package fr.upem.jee.allodoc.jpa;

import fr.upem.jee.allodoc.DatabaseManager;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Sirpapy on 14/12/2016.
 */
@Entity
@Table(name = "appointment")
@NamedQuery(name = "findByID",
        query = "Select a from Appointment a where a.id = :id")
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private Date beginHour;
    private Date endHour;
//    private Long appointment;

    @ManyToOne
    private Patient patient;
    public Appointment() {
    }
    public Appointment(long id) {

    }

    public Appointment(Date beginHour, Date endHour) {
        this.beginHour = Objects.requireNonNull(beginHour);
        this.endHour = Objects.requireNonNull(endHour);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(Date beginHour) {
        this.beginHour = beginHour;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

//    public void setAppointment(Long appointment) {
//        this.appointment = appointment;
//    }

//    public void removeAppointment() {
//
//    }
}
