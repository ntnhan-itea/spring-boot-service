package com.it.ntnhan.springboot.security;

import com.it.ntnhan.springboot.security.account.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@EqualsAndHashCode(of = {"username"})
public class UserDetailsImpl implements UserDetails {
    private String id;
    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private LocalDate validFrom;
    private LocalDate validTo;
    private LocalDateTime createdAt;


    @Transactional
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .enabled(user.isEnabled())
                .validFrom(user.getValidFrom())
                .validTo(user.getValidTo())
                .createdAt(user.getCreationTime())
                .build();

    }


}
