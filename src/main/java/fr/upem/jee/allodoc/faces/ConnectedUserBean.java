package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Resources;
import fr.upem.jee.allodoc.utilities.UserType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Optional;

/**
 * Created by raptao on 1/21/2017.
 */
@ManagedBean
@SessionScoped
public class ConnectedUserBean implements Serializable {

    private String connectedUsername;


    private boolean isPatient = true;


    private User connectedUser;

    public String getConnectedUsername() {
        if (connectedUsername == null) {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String name = req.getUserPrincipal().getName();
            connectedUsername = name;
        }
        return connectedUsername;
    }


    public Optional<User> getConnected() {
        String username = getConnectedUsername();
        Optional<User> connectedUserOptional = Optional.empty();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (req.isUserInRole(UserType.PATIENT.name())) {
            isPatient = true;
            PatientService patientService = new PatientService();
            connectedUserOptional = patientService.findByLogin(username);
            return connectedUserOptional;

        } else {
            isPatient = false;
            PhysicianService physicianService = new PhysicianService();
            connectedUserOptional = physicianService.findByLogin(username);
            if (!connectedUserOptional.isPresent()) {
                return Optional.empty();
            }
            return connectedUserOptional;
        }
    }

    public Patient getConnectedPatient() {
        if (getConnected().isPresent()) {
            connectedUser = getConnected().get();
            if (connectedUser != null) {
                PatientService patientService = new PatientService();
                return patientService.getById(connectedUser.getId());
            }
        }
        return null;
    }



    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }


    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public boolean isPatient() {
        return isPatient;
    }

    public void setPatient(boolean patient) {
        isPatient = patient;
    }

    @PostConstruct
    public void load() {
    }


}
