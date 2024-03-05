package com.unaux.dairo.api.dto;

import jakarta.validation.constraints.NotNull;

public record PersonUpdateDto(
        @NotNull
        int id,
        String name,
        String lastName,
        String phone,
        String birthday) {
}
