package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class Availability implements Serializable {

    @Id @GeneratedValue
    private Long id;

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
