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
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private Date beginHour;
    private Date endHour;
    DatabaseManager manager = new DatabaseManager("DEV-MODE");


    public Appointment() {
    }

    public Appointment(Date beginHour, Date endHour) {
        this.beginHour = Objects.requireNonNull(beginHour);
        this.endHour = Objects.requireNonNull(endHour);
    }


    public boolean isFree() {
        //TODO
        return true;
    }

}
