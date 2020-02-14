package com.person.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class Person {

    @JsonProperty
    private final Long id;

    @JsonProperty("first_name")
    private final String firstName;

    @JsonProperty("last_name")
    private final String lastName;

    @JsonProperty
    private final int age;

    @JsonProperty("favourite_colour")
    private final String favouriteColour;

    private final Set<String> hobby;

    public Person(Long id, String firstName, String lastName, int age, String favouriteColour, Set<String> hobby) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
        this.hobby = hobby;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public Set<String> getHobby() {
        return hobby;
    }

}