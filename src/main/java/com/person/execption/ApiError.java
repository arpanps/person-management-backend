package com.person.execption;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class ApiError {

    private final String errorMessage;

    public ApiError(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
