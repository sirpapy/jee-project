package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(eager = true)
@RequestScoped
public class WelcomeBean implements Serializable {

    @ManagedProperty("#{registerBean}")
    private RegisterBean registerBean;

    public WelcomeBean() {
        System.out.println("WelcomeBean instantiated");
    }

    public String getMessage() {
        return "I'm alive!";
    }

    public String registerPhysician() {
        registerBean.setType(RegisterBean.TYPE_PHYSICIAN);
        return Pages.PAGE_REGISTER + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public RegisterBean getRegisterBean() {
        return registerBean;
    }

    public void setRegisterBean(RegisterBean registerBean) {
        this.registerBean = registerBean;
    }

    public String registerPatient() {
        registerBean.setType(RegisterBean.TYPE_PATIENT);
        return Pages.PAGE_REGISTER + Pages.TAG_AVOIDING_EXPIRED_VIEW;

    }

}