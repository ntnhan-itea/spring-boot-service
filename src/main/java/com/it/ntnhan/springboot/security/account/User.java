package com.it.ntnhan.springboot.security.account;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_SYS_USER")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_enable", nullable = false)
    private boolean enable;

    @ManyToMany
    @JoinTable(
            name = "T_USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
