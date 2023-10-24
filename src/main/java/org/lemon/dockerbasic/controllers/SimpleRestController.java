package org.lemon.dockerbasic.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lemon.dockerbasic.model.Person;
import org.lemon.dockerbasic.services.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SimpleRestController {

    private final PersonService personService;

    @GetMapping("/person/{name}")
    public Person getPerson(@PathVariable String name) {
        log.info("name: {}", name);
        return personService.findPerson(name);
    }

}
