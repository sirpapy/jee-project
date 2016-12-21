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



    public Appointment(){
    }

    public Appointment(Date beginHour, Date endHour){
        Objects.requireNonNull(beginHour);
        Objects.requireNonNull(endHour);
        this.beginHour = beginHour;
        this.endHour = endHour;
    }


    public boolean isFree(){
        //TODO
        //TypedQuery<User> query = manager.getEntityManager().createQuery("Select u from appointment a where u.firstName='thierry' and u.lastName='rabearijao'", User.class);
        //User singleResult = query.getResultList().get(0);
        return true;
    }

}
