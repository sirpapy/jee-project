package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(eager = true)
@RequestScoped
public class WelcomeBean implements Serializable {

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    public WelcomeBean() {
        System.out.println("WelcomeBean instantiated");
    }

    public String getMessage() {
        return "I'm alive!";
    }

    public String registerPhysician() {
        userBean.setType(UserBean.TYPE_PHYSICIAN);
        return Pages.PAGE_REGISTER + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String registerPatient() {
        userBean.setType(UserBean.TYPE_PATIENT);
        return Pages.PAGE_REGISTER + Pages.TAG_AVOIDING_EXPIRED_VIEW;

    }


}