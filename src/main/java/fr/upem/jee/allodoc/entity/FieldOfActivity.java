package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(name = "unique_field_of_activity_name",
        columnNames = {"name"}))
@NamedQueries({
        @NamedQuery(name = "findFAByName", query = "select fa from FieldOfActivity fa where name = :fa_name"),
        @NamedQuery(name = "findAllFA", query = "select fa from FieldOfActivity fa")

})
public class FieldOfActivity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public FieldOfActivity(String fieldOfActivityName) {
        Objects.requireNonNull(fieldOfActivityName);
        Preconditions.checkArgument(!fieldOfActivityName.isEmpty(), "the field of activity name should not be empty");
        this.name = StringUtils.capitalize(fieldOfActivityName);
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
