package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(eager = true)
public class WelcomeBean implements Serializable {

    public WelcomeBean() {
        System.out.println("WelcomeBean instantiated");
    }

    public String getMessage() {
        return "I'm alive!";
    }

    public String register() {
        return Pages.PAGE_REGISTER + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }
}