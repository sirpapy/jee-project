package fr.upem.jee.allodoc.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name) &&
                Objects.equals(accounts, role.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
