package org.lemon.dockerbasic.services;

import org.lemon.dockerbasic.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public Person findPerson(String firstName) {
        return new Person(firstName, "Jones");
    }
}
