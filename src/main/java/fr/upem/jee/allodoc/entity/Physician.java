package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.service.FieldOfActivityService;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@NamedQueries({

        @NamedQuery(name = "getPhysicianFromId",
                query = "SELECT p from Physician p where p.id = :pId"),

        @NamedQuery(name = "findPhysicianFirstnameLastName",
                query = "SELECT p from Physician p where p.firstName = :pFirstName and p.lastName = :pLastName"),


        @NamedQuery(name = "findPhysicianName",
                query = "SELECT p from Physician p where p.firstName like :pName or p.lastName like :pName"),

        @NamedQuery(name = "findPhysicianFieldOfActivity",
                query = "SELECT p from Physician p where p.fieldOfActivity.name like :pField"),

        @NamedQuery(name = "findPhysicianByNameFieldOfActivityLocation",
                query = "SELECT p from Physician p where p.fieldOfActivity.name like :pField and  p.practiceArea.city = :pCity and p.firstName like :pName or p.lastName like :pName "),
})

public class Physician extends User implements Serializable {

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    private FieldOfActivity fieldOfActivity;

    @OneToOne(cascade = CascadeType.ALL)
    private Location practiceArea;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "physician_availability", joinColumns = @JoinColumn(name = "physician_id"), inverseJoinColumns = @JoinColumn(name = "availability_id"))
    private List<Availability> availabilities;

    public Physician() {
    }

    private Physician(String firstName, String lastName, String status, FieldOfActivity fieldOfActivity, Location practiceArea) {
        super(firstName, lastName, null, null, null, null);
        this.fieldOfActivity = Objects.requireNonNull(fieldOfActivity);
        this.status = Objects.requireNonNull(status);
        this.practiceArea = Objects.requireNonNull(practiceArea);
    }


    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public FieldOfActivity getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Location getPracticeArea() {
        return practiceArea;
    }

    public void setPracticeArea(Location practiceArea) {
        this.practiceArea = practiceArea;
    }

    public void setAvailability(Availability availability) {
        Preconditions.checkNotNull(availability);
        if (this.availabilities == null) {
            this.availabilities = new ArrayList<>();
        }
        this.availabilities.add(availability);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static class Builder {
        private String firstName;
        private String lastName;
        private FieldOfActivity fieldOfActivity;
        private String status;
        private Date birthDate;
        private Address address;
        private String email;
        private String password;
        private Location practiceArea;

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setPracticeArea(Location practiceArea) {
            this.practiceArea = practiceArea;
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

        public Builder setFieldOfActivity(FieldOfActivity fieldOfActivity) {
            Optional<FieldOfActivity> byName = FieldOfActivityService.getByName(fieldOfActivity.getName());
            this.fieldOfActivity = fieldOfActivity;

            return this;
        }


        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Physician build() {
            Physician physician = new Physician(firstName, lastName, status, fieldOfActivity, practiceArea);
            physician.setEmail(email);
            physician.setPassword(password);
            physician.setBirthDate(birthDate);
            physician.setAddress(address);
            return physician;
        }

        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }
    }

}
