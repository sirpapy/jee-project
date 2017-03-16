package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by raptao on 1/24/2017.
 */
@Entity
public class Account {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "role_name"))

    private List<Role> roles;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = Preconditions.checkNotNull(userName, "username shouldn't be null");
        this.password = Preconditions.checkNotNull(password, "password shouldn't be null");
    }

    public Role getRole(){
        if( roles == null || roles.size() <= 0){
            throw new IllegalStateException("there is no role for this user");
        }
        return roles.get(0);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(userName, account.userName) &&
                Objects.equals(password, account.password) &&
                Objects.equals(roles, account.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void addRole(Role role) {
        if( this.roles == null){
            this.roles = new ArrayList<>();
        }
        roles.add(role);
    }
}
