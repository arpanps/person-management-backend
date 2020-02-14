package com.person.service;

import java.util.List;

import com.person.models.Person;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public interface PersonService {

    Person getPerson(Long personId);

    List<Person> getPersons();

    Person createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Long personId);

}