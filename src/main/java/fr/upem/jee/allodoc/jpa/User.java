package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
import java.io.IOException;
import java.util.Objects;

/**
 * {@link User} is an {@link Entity} corresponding the one entry in the USER table of the AlloDoc database.
 * Created by raptao on 12/13/2016.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @OneToOne
    private Address address;
    private String password;

    public User(String firstName, String lastName, String email, String phoneNumber, Address address, String password) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.address = Objects.requireNonNull(address);
        this.password = Objects.requireNonNull(password);
    }


    public User(String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
    }

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
