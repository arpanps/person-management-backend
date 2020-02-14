package com.person.execption;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public abstract class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
