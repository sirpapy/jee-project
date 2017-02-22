package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findFAByName", query = "select fa from FieldOfActivity fa where name = :fa_name"),

})
public class FieldOfActivity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public FieldOfActivity(String fieldOfActivityName) {
        Objects.requireNonNull(fieldOfActivityName);
        Preconditions.checkArgument(!fieldOfActivityName.isEmpty(), "the field of activity name should not be empty");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldOfActivity that = (FieldOfActivity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
