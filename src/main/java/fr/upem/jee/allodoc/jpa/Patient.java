package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sirpapy on 14/12/2016.
 */

@Entity
@Table(name = "patient")
public class Patient extends User{

    @OneToMany(mappedBy="patient")
    Collection<Appointment> listOfAppointment =  new ArrayList<>();
    public Patient() {
        super();
    }
    public Patient(String firstName, String lastName, String email, String phoneNumber, Address address, String password) {
        super(firstName, lastName, email, phoneNumber, address, password);
    }
    public void addAppointment(Appointment a){
        listOfAppointment.add(a);
    }
    public void remoteAppointment(Appointment a){
        listOfAppointment.remove(a);
    }
    public Collection<Appointment> getListOfAppointment(){
        return listOfAppointment;
    }
}
