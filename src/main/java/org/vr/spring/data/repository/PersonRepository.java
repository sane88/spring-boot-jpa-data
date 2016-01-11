package org.vr.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.vr.spring.data.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String name);
}
