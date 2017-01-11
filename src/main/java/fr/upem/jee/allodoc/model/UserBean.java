package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.controller.UserController;
import fr.upem.jee.allodoc.jpa.User;
import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;

/**
 * Created by raptao on 1/10/2017.
 */
@ManagedBean
public class UserBean {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserController userController;

    public UserBean() {
        userController = new UserController();
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String authenticate(){
        return Pages.PAGE_HOME;
    }

    public String register(){
        User u = new User(firstName, lastName);
        userController.save(u);
        return Pages.PAGE_HOME;
    }


}
