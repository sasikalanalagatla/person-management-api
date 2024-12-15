package com.numeroalpha.person_management_api.service;

import com.numeroalpha.person_management_api.exceptions.PersonNotFoundException;
import com.numeroalpha.person_management_api.model.Person;

public interface PersonService {
    /**
     * Creates a new person.
     *
     * @param person The Person object containing details to be saved.
     * @return The saved Person object with an assigned ID.
     */
    Person createPerson(Person person);

    /**
     * Retrieves a person by their unique ID.
     *
     * @param personId The ID of the person to retrieve.
     * @return The Person object if found.
     * @throws PersonNotFoundException If no person exists with the given ID.
     */
    Person getPersonById(Integer personId);

    /**
     * Deletes a person by their unique ID.
     *
     * @param personId The ID of the person to delete.
     * @throws PersonNotFoundException If no person exists with the given ID.
     */
    void deletePersonById(Integer personId);
}
