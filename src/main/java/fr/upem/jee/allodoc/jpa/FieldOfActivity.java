package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(name = "unique_field_of_activity_name",
        columnNames = {"name"}))
@NamedQueries({
        @NamedQuery(name = "findFAByName", query = "select fa from FieldOfActivity fa where name = :fa_name"),
        @NamedQuery(name = "findAllFA", query = "select fa from FieldOfActivity")

})
public class FieldOfActivity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public FieldOfActivity(String fieldOfActivityName) {
        this.name = fieldOfActivityName;
    }

    public FieldOfActivity() {
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
