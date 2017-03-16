package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * {@link User} is an {@link Entity} corresponding the one entry in the USER table of the AlloDoc database.
 * Created by raptao on 12/13/2016.
 */

@Entity
@Table(name = "users")
// TODO
@NamedQuery(name = "getUserByLogin", query = "Select u from User u where u.account.userName= :userEmail")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(account, user.account) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, account, birthDate, address);
    }

    User(String firstName, String lastName, Address address, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.account = account;
    }

    public User() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        Preconditions.checkNotNull(account, "account shouldn't be null");
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
