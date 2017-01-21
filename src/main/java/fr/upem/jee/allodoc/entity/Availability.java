package fr.upem.jee.allodoc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class Availability implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginHour;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endHour;

    @ManyToMany(mappedBy = "availabilities")
    private List<Physician> physicians;

//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    Date date;


    public Date getBeginAvailability() {
        return beginHour;
    }

    public Date getEndAvailability() {
        return endHour;
    }

    public void setDate(Date beginHour, Date endHour) {
        this.beginHour = beginHour;
        this.endHour = endHour;
    }

    public Availability() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Availability(Date beginHour, Date endHour) {
        this.beginHour = Objects.requireNonNull(beginHour);
        this.endHour = Objects.requireNonNull(endHour);
    }
}
