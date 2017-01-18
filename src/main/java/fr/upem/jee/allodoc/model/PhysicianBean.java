package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.controller.PhysicianController;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.utilities.Pages;

import java.util.Optional;

/**
 * Created by raptao on 1/14/2017.
 */
public class PhysicianBean {

    private Physician physician;
    private String password;
    private String email;
    private PhysicianController controller;

    public PhysicianBean() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String authenticate() {
        Optional<User> authenticate = controller.authenticate(email, password);
        if( !authenticate.isPresent()){
            return Pages.PAGE_LOGIN_FORM + Pages.TAG_AVOIDING_EXPIRED_VIEW;
        }
        User u = authenticate.get();
        physician = PhysicianController.getFromId(u.getId());
        return Pages.PAGE_HOME;
    }
}
