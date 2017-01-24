package fr.upem.jee.allodoc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by raptao on 1/24/2017.
 */
@Entity
@Table
public class UserRole {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public UserRole() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
