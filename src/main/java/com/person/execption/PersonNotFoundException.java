package com.person.execption;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class PersonNotFoundException extends EntityNotFoundException {
    public PersonNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}