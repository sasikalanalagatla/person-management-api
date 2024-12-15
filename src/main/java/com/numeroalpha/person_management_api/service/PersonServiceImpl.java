package com.numeroalpha.person_management_api.service;
import com.numeroalpha.person_management_api.exceptions.PersonNotFoundException;
import com.numeroalpha.person_management_api.model.Person;
import com.numeroalpha.person_management_api.repository.PersonAddressRepository;
import com.numeroalpha.person_management_api.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PersonServiceImpl implements PersonService{ private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonAddressRepository personAddressRepository;

    @Override
    public Person createPerson(Person person) {
        LOGGER.info("Starting person creation process for: {}", person);

        try {
            if (person.getPersonAddress() != null) {
                LOGGER.info("Saving address for person: {}", person.getPersonAddress());
                personAddressRepository.save(person.getPersonAddress());
            }
            LOGGER.info("Saving person: {}", person);
            Person savedPerson = personRepository.save(person);

            LOGGER.info("Person created successfully with ID: {}", savedPerson.getPersonId());
            return savedPerson;
        } catch (Exception e) {
            LOGGER.error("Error occurred while creating person: {}", person, e);
            throw e;
        }
    }

    @Override
    public Person getPersonById(Integer personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with ID " + personId + " not found"));
    }

    @Override
    public void deletePersonById(Integer personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with ID " + personId + " not found"));
        personRepository.delete(person);
    }
}
