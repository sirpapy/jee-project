package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.utilities.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sirpapy on 14/12/2016.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "findPatientFirstnameLastName",
                query = "SELECT p from Patient p where p.firstName = :pFirstName and p.lastName = :pLastName"),

})
public class Patient extends User implements Serializable {
    @OneToMany(cascade = {CascadeType.ALL})
    List<Appointment> appointments;

    @OneToMany(cascade = {CascadeType.ALL})
    List<SearchItem> searchItems;

    public Patient() {
        super();
    }

    public Patient(String firstName, String lastName, Address address, Account account) {
        super(firstName, lastName, address, account);
    }

    public List<SearchItem> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }

    public void addSearchItem(SearchItem item){
        Preconditions.checkNotNull(item, "item should not be null");
        if (searchItems == null) {
            searchItems = new ArrayList<>();
        }
        searchItems.add(item);
    }
    public void addAppointment(Appointment newAppointment) {
        Preconditions.checkNotNull(newAppointment, "newAppointment should not be null");
        if (appointments == null) {
            appointments = new ArrayList<>();
        }
        appointments.add(newAppointment);
    }

    public void removeAppointment(long id) {
        Appointment toRemove = null;
        for (Appointment ap : appointments) {
            if (ap.getId() == id) {
                toRemove = ap;
            }
        }
        if (toRemove != null)
            appointments.remove(toRemove);
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Address address;
        private Date birthDate;
        private Account account;

        public Builder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }


        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Patient build() {
            Patient patient = new Patient(firstName, lastName, address, account);
            patient.setBirthDate(birthDate);
            patient.account.addRole(new Role(UserType.PATIENT.name()));
            return patient;
        }
    }
}
