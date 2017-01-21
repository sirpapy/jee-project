package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.service.UserService;
import fr.upem.jee.allodoc.utilities.Pages;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Optional;

/**
 * Created by raptao on 1/21/2017.
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    @ManagedProperty("#{connectedUserBean}")
    private ConnectedUserBean connectedUserBean;

    private String email;
    private String password;
    private String userType;
    private boolean asPhysician;

    public LoginBean() {

    }

    public boolean isAsPhysician() {
        return asPhysician;
    }

    public void setAsPhysician(boolean asPhysician) {
        this.asPhysician = asPhysician;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public ConnectedUserBean getConnectedUserBean() {
        return connectedUserBean;
    }

    public void setConnectedUserBean(ConnectedUserBean connectedUserBean) {
        this.connectedUserBean = connectedUserBean;
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

    public String signIn() {
        UserService userService = asPhysician ? new PhysicianService() : new PatientService();
        String homePage = asPhysician ? Pages.PAGE_PHYSICIAN_HOME : Pages.PAGE_PATIENT_HOME;
        Optional<User> authenticate = userService.authenticate(email, password);

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;

        // displaying message
        if (authenticate.isPresent()) {
            User user = authenticate.get();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", user.getLastName());
            context.addCallbackParam("loggedIn", true);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Invalid credentials");
            context.addCallbackParam("loggedIn", false);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);


        // TODO dosomething on connectedUserBean
        return authenticate.map(user -> homePage).orElse(Pages.PAGE_LOGIN_FORM);
    }

    public void addLoginTypeMessage() {
        String summary = String.format("You will be logged as a :\n%s", asPhysician ? "PHYSICIAN" : "PATIENT");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Info", summary));
    }
}
