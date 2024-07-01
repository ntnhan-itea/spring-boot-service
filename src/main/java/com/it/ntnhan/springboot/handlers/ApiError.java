package com.it.ntnhan.springboot.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private Integer status;

    private String message;

    private List<String> messages;

    @Builder.Default
    private Timestamp timestamp = Timestamp.from(Instant.now());

    private String path;

    Map<String, String> validationErrors;
}
