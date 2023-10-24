package org.lemon.dockerbasic.repositories;

import org.lemon.dockerbasic.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<Person, Long> {
    Person findFirstByFirstName(String firstName);
}
