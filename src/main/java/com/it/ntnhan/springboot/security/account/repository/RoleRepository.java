package com.it.ntnhan.springboot.security.account.repository;

import com.it.ntnhan.springboot.security.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("From Role WHERE name = 'ROLE_ADMIN'")
    Role getAdminOne();

    @Query("From Role WHERE name = 'ROLE_USER'")
    Role getUserOne();
}
