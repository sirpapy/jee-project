package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
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

    @SuppressWarnings("unchecked")
    public String signIn() {
        return asPhysician ? signInPhysician() : singInPatient();
    }

    private String singInPatient() {
        PatientService patientService = new PatientService();
        Optional<User> authenticate = patientService.authenticate(email, password);
        if (authenticate.isPresent()) {
            User patient = authenticate.get();
            Patient fromId = PatientService.getById(patient.getId());
            // no physician with this id
            if (fromId == null) {
                return failedLogin();
            }
            completeLogin(patient.getLastName());
            return Pages.PAGE_PATIENT_HOME;
        }
        return failedLogin();

    }

    private String signInPhysician() {
        PhysicianService physicianService = new PhysicianService();
        Optional<User> authenticate = physicianService.authenticate(email, password);
        if (authenticate.isPresent()) {
            User physician = authenticate.get();
            Physician fromId = PhysicianService.getById(physician.getId());
            // no physician with this id
            if (fromId == null) {
                return failedLogin();
            }
            completeLogin(physician.getLastName());
            return Pages.PAGE_PHYSICIAN_HOME;
        }
        return failedLogin();
    }

    private void completeLogin(String userName) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage welcome = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", userName);
        FacesContext.getCurrentInstance().addMessage(null, welcome);
        context.addCallbackParam("loggedIn", true);
    }

    private String failedLogin() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Invalid credentials");
        FacesContext.getCurrentInstance().addMessage(null, error);
        context.addCallbackParam("loggedIn", false);
        return Pages.PAGE_LOGIN_FORM;
    }

    public void addLoginTypeMessage() {
        String summary = String.format("You will be logged as a :\n%s", asPhysician ? "PHYSICIAN" : "PATIENT");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Info", summary));
    }
}
