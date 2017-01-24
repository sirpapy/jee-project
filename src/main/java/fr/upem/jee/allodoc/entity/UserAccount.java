package fr.upem.jee.allodoc.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by raptao on 1/24/2017.
 */
@Entity
@Table(name = "roles")
public class UserAccount {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String password;

    @ManyToMany
    private List<UserRole> roles;

    public UserAccount() {
    }

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
