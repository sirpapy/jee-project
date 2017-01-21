package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(eager = true)
@RequestScoped
public class WelcomeBean implements Serializable {

    @ManagedProperty("#{registerBean}")
    private RegisterBean registerBean;

    public WelcomeBean() {
    }

    public String getMessage() {
        return "I'm alive!";
    }

    public RegisterBean getRegisterBean() {
        return registerBean;
    }

    public void setRegisterBean(RegisterBean registerBean) {
        this.registerBean = registerBean;
    }

    public String registerPhysician() {
        initializeRegisterBean();
        this.registerBean.setType(RegisterBean.TYPE_PHYSICIAN);
        return Pages.PAGE_REGISTER + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public String registerPatient() {
        initializeRegisterBean();
        registerBean.setType(RegisterBean.TYPE_PATIENT);
        return Pages.PAGE_REGISTER + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public String loginPage() {
        return Pages.PAGE_LOGIN_FORM;
    }

    private void initializeRegisterBean() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("registerBean", new RegisterBean());
        this.registerBean = (RegisterBean) sessionMap.get("registerBean");
    }
}