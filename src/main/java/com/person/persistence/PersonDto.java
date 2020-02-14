package com.person.persistence;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Entity
@Table(name = "person")
public class PersonDto {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column
    private int age;

    @Column(nullable = false, name = "favourite_colour")
    private String favouriteColour;

    @ElementCollection
    @CollectionTable
    private Set<String> hobby;

    public PersonDto() {
        // Default constructor for Hibernate.

    }

    public PersonDto(String firstName, String lastName, int age, String favouriteColour, Set<String> hobby) {
        super();
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