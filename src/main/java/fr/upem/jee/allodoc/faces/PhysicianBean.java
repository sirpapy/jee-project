package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.utilities.Resources;

import java.util.Optional;

/**
 * Created by raptao on 1/14/2017.
 */
public class PhysicianBean {

    private Physician physician;
    private String password;
    private String email;
    private PhysicianService controller;

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
            return Resources.PAGE_LOGIN_FORM + Resources.TAG_AVOIDING_EXPIRED_VIEW;
        }
        User u = authenticate.get();
        physician = PhysicianService.getById(u.getId());
        return Resources.PAGE_INDEX;
    }


}
