package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class FieldOfActivity implements Serializable {

    @Id @GeneratedValue
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public FieldOfActivity(String fieldOfActivityName) {
        this.name = fieldOfActivityName;
    }

    public FieldOfActivity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
