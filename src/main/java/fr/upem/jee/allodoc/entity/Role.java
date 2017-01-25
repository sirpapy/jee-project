package fr.upem.jee.allodoc.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by raptao on 1/24/2017.
 */
@Entity
public class Role {
    @Id
    private String name;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "name"))
    private List<Account> accounts;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
