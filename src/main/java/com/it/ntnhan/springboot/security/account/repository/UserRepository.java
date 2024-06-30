package com.it.ntnhan.springboot.security.account.repository;

import com.it.ntnhan.springboot.security.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("FROM User WHERE username = :username OR email = :email")
    Optional<User> findByUsernameOrEmail(@Param("username") String username,
                                         @Param("email") String email);
}

