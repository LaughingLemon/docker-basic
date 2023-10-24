package org.lemon.dockerbasic.services;

import lombok.RequiredArgsConstructor;
import org.lemon.dockerbasic.model.Person;
import org.lemon.dockerbasic.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    public final PersonRepository personRepository;

    public Person findPerson(String firstName) {
        return personRepository.findFirstByFirstName(firstName);
    }
}
