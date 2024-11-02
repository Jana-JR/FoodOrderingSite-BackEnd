package com.jana.repository;

import com.jana.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    // User entity and its unique identifiers type (private Long id;)

    public User findByEmail(String username);
}
