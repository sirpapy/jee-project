package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Sirpapy on 14/12/2016.
 */

@Entity
@Table(name = "patient")
public class Patient extends User{

    @OneToMany(mappedBy="patient")
    Collection<Appointment> appointments =  new ArrayList<>();

    public Patient() {
        super();
    }

    public Patient(String firstName, String lastName, String email, String phoneNumber, Address address, String password) {
        super(firstName,lastName,phoneNumber,email,password,address);
    }
    public void addAppointment(Appointment a){
        appointments.add(a);
    }
    public void removeAppointment(Appointment a){
        appointments.remove(a);
    }
    public Collection<Appointment> getAppointments(){
        return appointments;
    }
}
