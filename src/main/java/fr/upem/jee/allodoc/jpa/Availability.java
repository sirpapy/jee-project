package fr.upem.jee.allodoc.jpa;

import com.google.common.base.Preconditions;

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

    @Column(nullable = false)
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

    public Availability(Date date){
        this.date = Preconditions.checkNotNull(date, "date should not be null");
    }
}
