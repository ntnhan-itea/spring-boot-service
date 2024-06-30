package com.it.ntnhan.springboot.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SignUpRequestDto {
    @NotBlank(message = "username should not be blank")
    private String username;

    @NotBlank(message = "password should not be blank")
    private String password;

    @NotBlank(message = "email should not be blank")
    @Email(message = "Invalid email format")
    private String email;

    public void standardize() {
        this.username = this.username.toLowerCase().trim();
        this.password = this.password.trim();
        this.email = this.email.toLowerCase().trim();
    }
}
