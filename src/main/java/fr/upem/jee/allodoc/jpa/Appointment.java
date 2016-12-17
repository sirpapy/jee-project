package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
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

    @OneToOne
    private Long idPatient;

    public Appointment(Date begin_hour, Date end_hour){
        this.begin_hour = begin_hour;
        this.end_hour = end_hour;
        this.idPatient = null;
    }
    public Appointment(Long id, Date begin_hour, Date end_hour, Long idPatient){
        this.id = id;
        this.begin_hour = begin_hour;
        this.end_hour = end_hour;
        this.idPatient = idPatient;
    }

    public void setAppointment(Long idPatient){
        this.idPatient = idPatient;
    }

    public void removeAppointment(){
        this.idPatient = null;
    }

    public boolean isFree(){
        return this.idPatient ==null?true:false;
    }
}
