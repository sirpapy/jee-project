package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
