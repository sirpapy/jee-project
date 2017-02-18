package fr.upem.jee.allodoc.faces;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.faces.PatientDashboardBean;
import fr.upem.jee.allodoc.faces.SearchBean;
import fr.upem.jee.allodoc.service.AppointmentService;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 * Created by Sirpapy on 18/02/2017.
 */


@ManagedBean(name = "patient_physicianAppointement", eager = true)
@RequestScoped
@SessionScoped
public class PhysicianAppointementForPatientBean {


    @ManagedProperty("#{patientDashboadBean}")
    private PatientDashboardBean patientDashboardBean;



    public String validateAppointment(int id) {
        Preconditions.checkArgument(id >= 0, "The user ID sent by search is not valid");
        Patient patient = PatientService.getById(id);
        PatientService patientService = new PatientService(patient);
        patientService.setNewAppointment(patientDashboardBean.getGetSelectedPhysician(), patientDashboardBean.getSelectAvailabilityID(), patientDashboardBean.getSelectAvailabilityID());
        patientService.save(patient);
        return Resources.PAGE_PATIENT_PROFIL + "?faces-redirect=true";
    }

    public PatientDashboardBean getPatientDashboardBean() {
        return patientDashboardBean;
    }

    public void setPatientDashboardBean(PatientDashboardBean patientDashboardBean) {
        this.patientDashboardBean = patientDashboardBean;

    }


}
