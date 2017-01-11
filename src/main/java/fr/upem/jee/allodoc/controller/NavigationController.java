package fr.upem.jee.allodoc.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by raptao on 1/10/2017.
 */
@ManagedBean(name = "navigationController", eager = true)
@SessionScoped
public class NavigationController {

    public String redirectLogin(){
        return "login";
    }

    public String registerPage(){
        return "register-form";
    }

}
