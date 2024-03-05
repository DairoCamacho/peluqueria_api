package com.unaux.dairo.api.repository;


import com.unaux.dairo.api.model.Person;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Page<Person> findByIsActiveTrue(Pageable paginacion);
}
