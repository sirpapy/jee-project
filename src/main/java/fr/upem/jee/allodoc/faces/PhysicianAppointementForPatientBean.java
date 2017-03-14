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





    public PatientDashboardBean getPatientDashboardBean() {
        return patientDashboardBean;
    }

    public void setPatientDashboardBean(PatientDashboardBean patientDashboardBean) {
        this.patientDashboardBean = patientDashboardBean;

    }


}
