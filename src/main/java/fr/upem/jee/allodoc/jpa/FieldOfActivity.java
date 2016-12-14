package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class FieldOfActivity {

    @Id @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
