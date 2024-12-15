package com.numeroalpha.person_management_api.controller;

import com.numeroalpha.person_management_api.exceptions.PersonNotFoundException;
import com.numeroalpha.person_management_api.model.Person;

import com.numeroalpha.person_management_api.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @PostMapping()
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        LOGGER.info("Received request to create a person: {}", person);
        try {
            Person createdPerson = personService.createPerson(person);
            LOGGER.info("Person created successfully: {}", createdPerson);
            return new ResponseEntity<>(createdPerson,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error creating person", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{personId}")
    public ResponseEntity<?> getPersonById(@PathVariable Integer personId) {
        LOGGER.info("Fetching person with ID: {}", personId);
        try {
            Person person = personService.getPersonById(personId);
            LOGGER.info("Person retrieved successfully: {}", person);

            if (person != null && person.getPersonAddress() != null) {
                return ResponseEntity.ok(person);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (PersonNotFoundException e) {
            LOGGER.error("Person with ID {} not found", personId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving person", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer personId) {
        LOGGER.info("Received request to delete person with ID: {}", personId);
            try {
                personService.deletePersonById(personId);
                LOGGER.info("Person deleted successfully with ID: {}", personId);
                return ResponseEntity.ok("Person deleted successfully.");
            } catch (PersonNotFoundException e) {
                LOGGER.error("Person with ID {} not found", personId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                LOGGER.error("Error occurred while deleting person", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
}
