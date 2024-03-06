package com.unaux.dairo.api.dto;

public record PersonResponseDto(
        int id,
        String name,
        String lastName,
        String phone,
        java.time.LocalDate birthday) {
}
