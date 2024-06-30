package com.it.ntnhan.springboot.security.service;

import com.it.ntnhan.springboot.security.UserDetailsImpl;
import com.it.ntnhan.springboot.security.account.User;
import com.it.ntnhan.springboot.security.account.repository.RoleRepository;
import com.it.ntnhan.springboot.security.account.repository.UserRepository;
import com.it.ntnhan.springboot.security.dto.SignInRequestDto;
import com.it.ntnhan.springboot.security.dto.SignInResponseDto;
import com.it.ntnhan.springboot.security.dto.SignUpRequestDto;
import com.it.ntnhan.springboot.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public void signUp(SignUpRequestDto requestDto) {
        requestDto.standardize();
        Optional<User> userExisted = userRepository.findByUsernameOrEmail(requestDto.getUsername(), requestDto.getEmail());

        if(userExisted.isPresent()) {
            throw new RuntimeException("Username or Email already existed");
        }

        User user = User.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .enabled(true)
                .roles(Set.of(roleRepository.getUserOne()))
                .build();

        userRepository.save(user);
    }

    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getUsername(), signInRequestDto.getPassword()));

//        TODO: checking
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return SignInResponseDto.builder()
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .id(userDetails.getId())
                .token(jwt)
                .type("Bearer")
                .roles(roles)
                .build();
    }

}
