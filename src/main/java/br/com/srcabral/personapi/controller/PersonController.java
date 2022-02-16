package br.com.srcabral.personapi.controller;


import br.com.srcabral.personapi.dto.request.PersonDTO;
import br.com.srcabral.personapi.dto.response.MessageResponseDTO;
import br.com.srcabral.personapi.entity.Person;
import br.com.srcabral.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {


    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // @Valid parametro
    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }
}
