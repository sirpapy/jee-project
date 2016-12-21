package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class FieldOfActivity {



    @Id @GeneratedValue
    private Long id;
    private String name;


    public FieldOfActivity() {

    }
    public FieldOfActivity(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}