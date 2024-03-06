package com.unaux.dairo.api.model;

import com.unaux.dairo.api.dto.PersonCreateDto;
import com.unaux.dairo.api.dto.PersonUpdateDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    private LocalDate birthday;
    @Column(name = "active")
    private boolean isActive;

    public Person(PersonCreateDto personCreateDto) {
        this.isActive = true;
        this.name = personCreateDto.name();
        this.lastName = personCreateDto.lastName();
        this.phone = personCreateDto.phone();
        this.birthday = LocalDate.parse(personCreateDto.birthday());
    }

    public void update(PersonUpdateDto personUpdateDto) {
        if (personUpdateDto.name() != null) {
            this.name = personUpdateDto.name();
        }
        if (personUpdateDto.lastName() != null) {
            this.lastName = personUpdateDto.lastName();
        }
        if (personUpdateDto.phone() != null) {
            this.phone = personUpdateDto.phone();
        }
        if (personUpdateDto.birthday() != null) {
            this.birthday = LocalDate.parse(personUpdateDto.birthday());
        }
    }

    public void inactivate() {
        this.isActive = false;
    }
}