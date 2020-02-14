package com.person.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public interface PersonRepository extends JpaRepository<PersonDto, Long> {

}