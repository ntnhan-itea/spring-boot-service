package com.it.ntnhan.springboot.security.account;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user_role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Role {
    @Id
    @UuidGenerator
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(ERole name) {
        this.name = name;
    }
}
