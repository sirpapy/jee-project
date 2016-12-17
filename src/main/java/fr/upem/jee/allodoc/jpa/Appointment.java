package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Sirpapy on 14/12/2016.
 */
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private Date begin_hour;
    private Date end_hour;

    public Appointment(Long id, Date begin_hour, Date end_hour){
        this.id = id;
        this.begin_hour = begin_hour;
        this.end_hour = end_hour;
    }
}
