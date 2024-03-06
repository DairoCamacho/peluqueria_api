package com.unaux.dairo.api.controller;

import com.unaux.dairo.api.dto.PersonCreateDto;
import com.unaux.dairo.api.dto.PersonFindDto;
import com.unaux.dairo.api.dto.PersonResponseDto;
import com.unaux.dairo.api.dto.PersonUpdateDto;
import com.unaux.dairo.api.model.Person;
import com.unaux.dairo.api.repository.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(
            @RequestBody @Valid PersonCreateDto personCreateDto,
            UriComponentsBuilder uriComponentsBuilder) {
        Person person = personRepository.save(new Person(personCreateDto));
        PersonResponseDto response = new PersonResponseDto(person.getId(), person.getName(), person.getLastName(), person.getPhone(), person.getBirthday());
        URI url = uriComponentsBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    /*
    @GetMapping
    public List<PersonFindDto> listPerson(){
        return personRepository.findAll().stream().map(PersonFindDto::new).toList();
    }
    */

    @GetMapping
    public ResponseEntity<Page<PersonFindDto>> listPerson(Pageable paginacion) {
        //return personRepository.findAll(paginacion).map(PersonFindDto::new);
        return ResponseEntity.ok(personRepository.findByIsActiveTrue(paginacion).map(PersonFindDto::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> findPerson(@PathVariable int id) {
        Person person = personRepository.getReferenceById(id);
        PersonResponseDto response = new PersonResponseDto(person.getId(), person.getName(), person.getLastName(), person.getPhone(), person.getBirthday());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PersonResponseDto> updatePerson(@RequestBody @Valid PersonUpdateDto personUpdateDto) {
        Person person = personRepository.getReferenceById(personUpdateDto.id());
        person.update(personUpdateDto);
        PersonResponseDto response = new PersonResponseDto(person.getId(), person.getName(), person.getLastName(), person.getPhone(), person.getBirthday());
        return ResponseEntity.ok(response);
    }

    /*
    // OJO: delete físico
    @DeleteMapping("/{id}")
    @Transactional
    public void deletePerson (@PathVariable int id){
        Person person = personRepository.getReferenceById(id);
        personRepository.delete(person);
    }
    */

    @DeleteMapping("/{id}") // Delete lógico
    @Transactional
    public ResponseEntity deletePerson(@PathVariable int id) {
        Person person = personRepository.getReferenceById(id);
        person.inactivate();
        return ResponseEntity.noContent().build();
    }
}
