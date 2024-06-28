package com.it.ntnhan.springboot.security.account;

import com.it.ntnhan.springboot.general.validity.Validity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_SYS_USER")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class User extends Validity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;

    @Column(name = "creation_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationTime;

    @Column(name = "modification_time")
    @UpdateTimestamp
    private LocalDateTime modificationTime;

    @ManyToMany
    @JoinTable(
            name = "T_USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
