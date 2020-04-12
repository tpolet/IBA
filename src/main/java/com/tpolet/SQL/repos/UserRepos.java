package com.tpolet.SQL.repos;

import com.tpolet.SQL.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepos extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
