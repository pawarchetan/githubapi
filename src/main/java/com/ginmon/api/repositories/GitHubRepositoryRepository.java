package com.ginmon.api.repositories;

import com.ginmon.api.domains.GitHubRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubRepositoryRepository  extends JpaRepository<GitHubRepository, String> {
    GitHubRepository findByName(String name);
}
