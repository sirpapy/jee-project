package fr.upem.jee.allodoc.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by raptao on 1/24/2017.
 */
@Entity
@Table(name = "user_roles")
@NamedQuery(name = "findRoleByName",
query = "select r from UserRole r where r.name = :rName")
public class UserRole {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "role_name")
    private String name;

    public UserRole() {
    }

    public UserRole(String roleName) {
        Objects.requireNonNull(roleName, "roleName should not be null");
        this.name = roleName.toUpperCase();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
