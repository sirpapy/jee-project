package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Sirpapy on 14/12/2016.
 */
@Entity
@Table(name = "patient")
public class Patient extends User{

    public Patient() {
        super();
    }
    public Patient(String firstName, String lastName, String email, String phoneNumber, Address address, String password) {
        super(firstName, lastName, email, phoneNumber, address, password);
    }
}
