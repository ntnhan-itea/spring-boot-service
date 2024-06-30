package com.it.ntnhan.springboot.community;

import com.it.ntnhan.springboot.security.dto.SignInRequestDto;
import com.it.ntnhan.springboot.security.dto.SignUpRequestDto;
import com.it.ntnhan.springboot.security.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationResource {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpRequestDto requestDto){
        authService.signUp(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@RequestBody @Valid SignInRequestDto signInRequestDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.signIn(signInRequestDto));
    }

}
