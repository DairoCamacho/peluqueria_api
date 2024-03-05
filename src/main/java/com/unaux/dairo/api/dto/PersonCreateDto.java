package com.unaux.dairo.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PersonCreateDto(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @NotBlank
        String phone,
        @NotBlank
        String birthday) {
}
