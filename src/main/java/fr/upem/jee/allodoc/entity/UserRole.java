package fr.upem.jee.allodoc.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by raptao on 1/24/2017.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserAccount> accounts;

    public UserRole() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserAccount> accounts) {
        this.accounts = accounts;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
