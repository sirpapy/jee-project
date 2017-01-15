package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.controller.UserController;
import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by raptao on 1/10/2017.
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    public static final String TYPE_PHYSICIAN = "PHYSICIAN";
    public static final String TYPE_PATIENT = "PATIENT";

    private String type;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private UserController userController;

    public UserBean() {
        userController = new UserController();
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    /**
     * Registers a new {@link Patient} in the database
     *
     * @return the redirection page ( login page )
     */
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
