package fr.upem.jee.allodoc.faces;

import javax.annotation.PostConstruct;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by raptao on 1/21/2017.
 */
@ManagedBean
@SessionScoped
public class ConnectedUserBean {

    private String connectedUsername;

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

    @PostConstruct
    public void load() {
    }

    }


    public String showPhysiciantProfil(int id){
        Physician physician = new Physician();
        physician.setLastName("NDIAYE DOC");
        physician.setFirstName("Pape");
        PhysicianService physicianService = new PhysicianService();
        physicianService.save(physician);
        this.id = physicianService.searchByName("NDIAYE").get(0).getId().intValue();
//        this.id = id;
        this.user = PhysicianService.getById(Long.valueOf(id));
        return Resources.PAGE_PHYSICIAN_PROFIL;
    }

    public ConnectedUserBean(){
    }
    public ConnectedUserBean(User user){
        this.user = user;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
