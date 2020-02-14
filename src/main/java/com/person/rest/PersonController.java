package com.person.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.person.models.Person;
import com.person.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@RestController
@RequestMapping("api/v1/")
@Tag(name = "Person", description = "REST APIs for person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(summary = "Get persons", description = "Api to get all person list.")
    @GetMapping(value = "/person/list")
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok(personService.getPersons());
    }

    @Operation(summary = "Get person", description = "Api to get person info by id")
    @GetMapping(value = "/person/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") final Long personId) {
        return ResponseEntity.ok(personService.getPerson(personId));
    }

    @Operation(summary = "Add person", description = "Api to add a new person")
    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(@RequestBody final Person personRequest) {
        final Person shopPerson = personService.createPerson(personRequest);
        final URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(shopPerson.getId()).toUri();
        return ResponseEntity.created(resourceLocation).body(shopPerson);
    }

    @Operation(summary = "Update person", description = "Api to update person ")
    @PutMapping(value = "/person/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") final String personId,
            @RequestBody final Person personRequest) {
        final Person shopPerson = personService.updatePerson(personRequest);
        return ResponseEntity.ok(shopPerson);
    }

    @Operation(summary = "Delete person", description = "Api to delete person ")
    @DeleteMapping(value = "/person/{personId}")
    public ResponseEntity<Person> deletePerson(@PathVariable("personId") final Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.ok().build();
    }
}