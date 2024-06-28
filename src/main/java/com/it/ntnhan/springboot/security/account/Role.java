package com.it.ntnhan.springboot.security.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_SYS_ROLE")
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
    @Column(name = "role_name", nullable = false, unique = true)
    private ERole name;

    @Column(name = "creation_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationTime;

    @Column(name = "modification_time")
    @UpdateTimestamp
    private LocalDateTime modificationTime;

    @ManyToMany(mappedBy = "roles")
    @Builder.Default
    private Set<User> users = new HashSet<>();

    public Role(ERole name) {
        this.name = name;
    }
}
