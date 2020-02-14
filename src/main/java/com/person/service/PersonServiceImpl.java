package com.person.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.person.execption.PersonNotFoundException;
import com.person.models.Person;
import com.person.persistence.PersonDto;
import com.person.persistence.PersonRepository;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    private PersonDto checkAndGet(final Long personId) {
        final Optional<PersonDto> personDto = personRepository.findById(personId);
        if (personDto.isPresent()) {
            return personDto.get();
        }

        throw new PersonNotFoundException(String.format("Person %s not found", personId));
    }

    @Override
    public Person getPerson(final Long personId) {
        return toModel(checkAndGet(personId));
    }

    @Override
    public List<Person> getPersons() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false).map(dto -> toModel(dto))
                .collect(Collectors.toList());
    }

    @Override
    public Person createPerson(Person person) {
        final PersonDto personDto = personRepository.save(toDto(person));
        return toModel(personDto);
    }

    @Override
    public Person updatePerson(Person person) {
        PersonDto dto = personRepository.save(toDto(person));
        return toModel(dto);
    }

    @Override
    public void deletePerson(final Long personId) {
        final PersonDto personDto = checkAndGet(personId);
        personRepository.delete(personDto);
    }

    private PersonDto toDto(final Person person) {
        return new PersonDto(person.getFirstName(), person.getLastName(), person.getAge(), person.getFavouriteColour(),
                person.getHobby());
    }

    private Person toModel(final PersonDto personDto) {

        return new Person(personDto.getId(), personDto.getFirstName(), personDto.getLastName(), personDto.getAge(),
                personDto.getFavouriteColour(), personDto.getHobby());
    }
}