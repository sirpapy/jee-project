package fr.upem.jee.allodoc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * {@link User} is an {@link Entity} corresponding the one entry in the USER table of the AlloDoc database.
 * Created by raptao on 12/13/2016.
 */

@Entity
@Table(name = "users")
// TODO
//@NamedQuery(name = "getAuthenticatedUser", query = "Select u from User u where u.email= :userEmail and u.account.password= :userPassword")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    Account account;

    Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    User(String firstName, String lastName, String phoneNumber, String email, String password, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.account = new Account(email, password);
        this.address = address;
    }

    public User() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public void setRole(Role role){
        account.addRole(role);
    }
}
