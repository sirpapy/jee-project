package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.controller.UserController;
import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by raptao on 1/10/2017.
 */
@ManagedBean
public class UserBean implements Serializable{
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

    public String authenticate() {
        return Pages.PAGE_HOME;
    }

    public String registerAsPatient() {
        Patient p = new Patient.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .build();
        userController.save(p);
        return Pages.PAGE_LOGIN_FORM + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public String registerAsPhysician() {
        return Pages.PAGE_LOGIN_FORM;
    }


}
