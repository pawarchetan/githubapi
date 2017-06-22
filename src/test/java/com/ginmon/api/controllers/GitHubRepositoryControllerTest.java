package com.ginmon.api.controllers;

import com.ginmon.api.domains.GitHubRepository;
import com.ginmon.api.exceptions.InvalidGitHubRepositoryException;
import com.ginmon.api.repositories.GitHubRepositoryRepository;
import com.ginmon.api.services.GitHubRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(GitHubRepositoryController.class)
public class GitHubRepositoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GitHubRepositoryService gitHubRepositoryService;

    @MockBean
    private GitHubRepositoryRepository gitHubRepositoryRepository;

    @Test
    public void shouldAcceptValidRequestForCreatingGitHubRepository() throws Exception {
        GitHubRepository repository = getGitHubRepository();
        when(gitHubRepositoryService.createGitHubRepositoryWithMetadata("pawarchetan", "GoogleAPI")).thenReturn(repository);
        mvc.perform(post("/github/repositories/owner/pawarchetan/repository/GoogleAPI")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldHandleInvalidGitHubRepositoryExceptionWhileCreatingGitHubRepository() throws Exception {
        doThrow(new InvalidGitHubRepositoryException("GitHub repository or user not found", 404))
                .when(gitHubRepositoryService).createGitHubRepositoryWithMetadata(anyString(), anyString());
        mvc.perform(post("/github/repositories/owner/pawarchetan/repository/Google")
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"statusCode\":404,\"message\":\"GitHub repository or user not found\"}"));
    }

    @Test
    public void testGetAllGitHubRepositories() throws Exception {
        List<GitHubRepository> gitHubRepositoryList = new ArrayList<>();
        GitHubRepository gitHubRepository = getGitHubRepository();
        gitHubRepositoryList.add(gitHubRepository);
        when(gitHubRepositoryService.getAllGitHubRepositories()).thenReturn(gitHubRepositoryList);
        mvc.perform(get("/github/repositories")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":null,\"owner\":null,\"name\":\"pawarchetan/GoogleAPI\",\"description\":\"Annotation Based Spring MVC Configuration to show Geo Coordinates on Web browser\",\"clone_url\":\"https://github.com/pawarchetan/GoogleAPI.git\",\"ssh_url\":null,\"html_url\":null,\"stargazers_count\":0,\"created_at\":null}]"));
    }

    @Test
    public void testGetGitHubRepositoryMetadataByName() throws Exception {
        GitHubRepository gitHubRepository = getGitHubRepository();
        when(gitHubRepositoryService.getGitHubRepositoryMetadataByName("pawarchetan/GoogleAPI")).thenReturn(gitHubRepository);
        mvc.perform(get("/github/repositories/repository/GoogleAPI")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    private GitHubRepository getGitHubRepository(){
        GitHubRepository repository = new GitHubRepository();
        repository.setCloneUrl("https://github.com/pawarchetan/GoogleAPI.git");
        repository.setStars(0);
        repository.setDescription("Annotation Based Spring MVC Configuration to show Geo Coordinates on Web browser");
        repository.setName("pawarchetan/GoogleAPI");
        return repository;
    }
}
