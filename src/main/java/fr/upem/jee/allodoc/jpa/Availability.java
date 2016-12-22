package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class Availability implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Set<PhysicianAvailability> physicians;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Availability() {

    }
}
