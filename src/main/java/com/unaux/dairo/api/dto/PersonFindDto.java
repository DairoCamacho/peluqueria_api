package com.unaux.dairo.api.dto;

import com.unaux.dairo.api.model.Person;

import java.time.LocalDate;

public record PersonFindDto(int id, String name, String lastName, String phone, LocalDate birthday) {
    public PersonFindDto (Person person){
        this(person.getId(), person.getName(), person.getLastName(), person.getPhone(), person.getBirthday());
    }
}
