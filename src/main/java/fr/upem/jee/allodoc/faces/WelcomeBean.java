package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.utilities.Resources;
import fr.upem.jee.allodoc.utilities.UserType;

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
        this.registerBean.setType(UserType.PHYSICIAN.name());
        return Resources.PAGE_REGISTER + Resources.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public String registerPatient() {
        initializeRegisterBean();
        registerBean.setType(UserType.PATIENT.name());
        return Resources.PAGE_REGISTER + Resources.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public String loginPage() {
        return Resources.PAGE_LOGIN_FORM;
    }

    private void initializeRegisterBean() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("registerBean", new RegisterBean());
        this.registerBean = (RegisterBean) sessionMap.get("registerBean");
    }
}