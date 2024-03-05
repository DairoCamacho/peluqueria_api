package com.unaux.dairo.api.controller;

import com.unaux.dairo.api.dto.PersonCreateDto;
import com.unaux.dairo.api.dto.PersonFindDto;
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

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public void createPerson (@RequestBody @Valid PersonCreateDto personCreateDto){
        personRepository.save(new Person(personCreateDto));
    }
    @GetMapping
    public Page<PersonFindDto> listPerson(Pageable paginacion){
        //return personRepository.findAll(paginacion).map(PersonFindDto::new);
        return personRepository.findByIsActiveTrue(paginacion).map(PersonFindDto::new);
    }

    /*
    @GetMapping
    public List<PersonFindDto> listPerson(){
        return personRepository.findAll().stream().map(PersonFindDto::new).toList();
    }
    */

    @PutMapping
    @Transactional
    public void updatePerson (@RequestBody @Valid PersonUpdateDto personUpdateDto){
        Person person = personRepository.getReferenceById(personUpdateDto.id());
        person.update(personUpdateDto);
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
    public void deletePerson (@PathVariable int id){
        Person person = personRepository.getReferenceById(id);
        person.deactivate();
    }
}
