package com.ginmon.api.services;


import com.ginmon.api.domains.GitHubRepository;

import java.util.List;

public interface GitHubRepositoryService {

    GitHubRepository createGitHubRepositoryWithMetadata(String owner, String repositoryName);
    GitHubRepository getGitHubRepositoryMetadataByName(String repositoryName);
    List<GitHubRepository> getAllGitHubRepositories();
}
