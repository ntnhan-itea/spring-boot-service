package com.it.ntnhan.springboot.security.dto;


import lombok.Getter;

@Getter
public enum ClaimName {
    ADDRESS("address"),
    EMAIL("email"),
    USER_ID("userId"),
    ROLES("roles"),
    ENABLE("enable"),
    TYPE("type"),
    VALID_FROM("validFrom"),
    VALID_TO("validTo"),
    CREATED_AT("createdAt"),
    ;

    private final String value;

    ClaimName(String value) {
        this.value = value;
    }
}
