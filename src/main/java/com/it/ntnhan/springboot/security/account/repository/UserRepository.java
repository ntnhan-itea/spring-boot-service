package com.it.ntnhan.springboot.security.account.repository;

import com.it.ntnhan.springboot.security.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
