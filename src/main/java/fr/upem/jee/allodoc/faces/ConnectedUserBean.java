package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.Appointment;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raptao on 1/21/2017.
 */
@ManagedBean
@SessionScoped
public class ConnectedUserBean {
    private int id;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String showPatientProfil(int id){
        this.id = id;
        this.user = PatientService.getById(id);
        return Resources.PAGE_PATIENT_PROFIL;
    }

    public List<Appointment> getAppointments(){
        return PatientService.getById(id).getAppointments().stream().collect(Collectors.toList());

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
