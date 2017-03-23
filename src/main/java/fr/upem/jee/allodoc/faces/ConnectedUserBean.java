package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.faces.patient.SearchHistoryService;
import fr.upem.jee.allodoc.faces.patient.SearchItem;
import fr.upem.jee.allodoc.service.AppointmentService;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Resources;
import fr.upem.jee.allodoc.utilities.UserType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
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
    private String badgeLabel;

    @ManagedProperty("#{searchHistoryService}")
    private SearchHistoryService searchHistoryService;

    public SearchHistoryService getSearchHistoryService() {
        return searchHistoryService;
    }

    public void setSearchHistoryService(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }

    public String getBadgeLabel() {
        return getConnectedPatient().getAppointments().isEmpty() ?
                "No appointment" :
                "You have " + getConnectedPatient().getAppointments().size() + " appointment(s)";
    }

    public void setBadgeLabel(String badgeLabel) {
        this.badgeLabel = badgeLabel;
    }

    public User getConnectedUser() throws IOException {
        if (connectedUser == null) {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String userName = req.getUserPrincipal().getName();
            String userRole = req.isUserInRole(UserType.PATIENT.name()) ? UserType.PATIENT.name() : UserType.PHYSICIAN.name();
            PatientService patientService = new PatientService();
            Optional<User> byLogin = patientService.findByLogin(userName);
            if (byLogin.isPresent()) {
                connectedUser = byLogin.get();
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
            }
        }
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public String getConnectedUsername() {
        if (connectedUsername == null) {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String name = req.getUserPrincipal().getName();
            connectedUsername = name;
        }
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public Optional<User> getConnected() {
        String username = getConnectedUsername();
        Optional<User> connectedUserOptional;
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
                return PatientService.getById(connectedUser.getId());
            }
        }
        return null;
    }

    public boolean isPatient() {
        return isPatient;
    }

    public void setPatient(boolean patient) {
        isPatient = patient;
    }

    public String removeAppointment(long appointmentID) {
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.removeAppointment(connectedUser.getId(), appointmentID);
        return Resources.PAGE_PATIENT_PROFIL;
    }

    public List<SearchItem> getSearchHistory() {
        return searchHistoryService.searchItemsOf(getConnectedUsername());
    }

    @PostConstruct
    public void load() {
    }


}
