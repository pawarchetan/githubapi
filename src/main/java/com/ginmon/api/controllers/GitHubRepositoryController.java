package com.ginmon.api.controllers;

import com.ginmon.api.domains.GitHubRepository;
import com.ginmon.api.exceptions.InvalidGitHubRepositoryException;
import com.ginmon.api.services.GitHubRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/github/repositories")
public class GitHubRepositoryController {

    private final GitHubRepositoryService gitHubRepositoryService;

    public GitHubRepositoryController(GitHubRepositoryService gitHubRepositoryService) {
        this.gitHubRepositoryService = gitHubRepositoryService;
    }

    @PostMapping(value = "/owner/{owner}/repository/{repositoryName}", produces = "application/json")
    public ResponseEntity<GitHubRepository> createGitHubRepositoryWithMetadata(@PathVariable String owner, @PathVariable String repositoryName)
            throws InvalidGitHubRepositoryException {
        return new ResponseEntity<>(
                gitHubRepositoryService.createGitHubRepositoryWithMetadata(owner, repositoryName), HttpStatus.OK);
    }

    @GetMapping(value = "/repository/{repositoryName}", produces = "application/json")
    public ResponseEntity<GitHubRepository> getGitHubRepositoryMetadataByName(@PathVariable String repositoryName) throws InvalidGitHubRepositoryException {
        return new ResponseEntity<>(
                gitHubRepositoryService.getGitHubRepositoryMetadataByName(repositoryName), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<GitHubRepository>> getAllGitHubRepositories(){
        return new ResponseEntity<>(gitHubRepositoryService.getAllGitHubRepositories(), HttpStatus.OK);
    }
}
