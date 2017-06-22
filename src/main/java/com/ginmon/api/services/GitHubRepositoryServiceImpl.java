package com.ginmon.api.services;

import com.ginmon.api.domains.GitHubRepository;
import com.ginmon.api.exceptions.InvalidGitHubRepositoryException;
import com.ginmon.api.repositories.GitHubRepositoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Log4j2
public class GitHubRepositoryServiceImpl implements GitHubRepositoryService{

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    private final RestTemplate restTemplate;

    private final RetryTemplate retryTemplate;

    private final GitHubRepositoryRepository gitHubRepositoryRepository;

    public GitHubRepositoryServiceImpl(RestTemplate restTemplate, RetryTemplate retryTemplate, GitHubRepositoryRepository gitHubRepositoryRepository) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
        this.gitHubRepositoryRepository = gitHubRepositoryRepository;
    }

    @Override
    @Transactional
    public GitHubRepository createGitHubRepositoryWithMetadata(String repositoryOwner, String repositoryName)
            throws InvalidGitHubRepositoryException {
        String gitHubApiUrl = gitHubApiConstructor(repositoryOwner, repositoryName);
        log.info("GitHub API URL:" + gitHubApiUrl);
        try {
            return retryTemplate.execute(retryCallback -> {
                ResponseEntity<GitHubRepository> response =
                        this.restTemplate.getForEntity(gitHubApiUrl, GitHubRepository.class);
                return saveGitHubRepository(response.getBody());
            });
        } catch (HttpStatusCodeException httpException) {
            throw createGitHubRepositoryException(httpException);
        }
    }

    @Override
    @Transactional
    public GitHubRepository getGitHubRepositoryMetadataByName(String name) {
        log.info("Getting metadata for GitHub repository:" + name);
        return gitHubRepositoryRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<GitHubRepository> getAllGitHubRepositories(){
        log.info("Getting all GitHub Repositories...");
        return gitHubRepositoryRepository.findAll();
    }

    private GitHubRepository saveGitHubRepository(GitHubRepository gitHubRepository){
        return gitHubRepositoryRepository.save(gitHubRepository);
    }

    private String gitHubApiConstructor(String repositoryOwner, String repositoryName) {
        return String.join("/", gitHubApiUrl, repositoryOwner, repositoryName);
    }

    private InvalidGitHubRepositoryException createGitHubRepositoryException(
            HttpStatusCodeException httpException){
        String errorMessage = httpException.getLocalizedMessage();
        return new InvalidGitHubRepositoryException(errorMessage, httpException.getStatusCode().value());
    }
}
