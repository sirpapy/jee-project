package fr.upem.jee.allodoc.faces;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sirpapy on 19/01/2017.
 */
@RequestScoped
@ManagedBean
public class SearchBean implements Serializable {


    private String name;
    private FieldOfActivity fieldOfActivity;
    private Location postalCode;

    private int selectAvailabilityID;
    @ManagedProperty("#{connectedUserBean}")
    private ConnectedUserBean connectedUserBean;
    private List<Physician> physicianResultList;

    public SearchBean() throws IOException {
    }

    public int getSelectAvailabilityID() {
        return selectAvailabilityID;
    }

    public void setSelectAvailabilityID(int selectAvailabilityID) {
        this.selectAvailabilityID = selectAvailabilityID;
    }

    public List<Physician> getPhysicianResultList() {
        return physicianResultList;
    }

    public void setPhysicianResultList(List<Physician> physicianResultList) {
        if (this.physicianResultList == null) {
            this.physicianResultList = new ArrayList<>();
        }
        this.physicianResultList = physicianResultList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldOfActivity getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Location getPostalCode() {
        return postalCode;
    }


    public void setPostalCode(Location postalCode) {
        this.postalCode = postalCode;
    }

    public String startSearch() {
        PhysicianService physicianService = new PhysicianService();
        if (!isSet(name) && !postalCode.isSet() && !fieldOfActivity.isSet()) { // no value set
            physicianResultList = PhysicianService.getAll();
        } else if (isSet(name) && postalCode.isSet() && fieldOfActivity.isSet()) { // everything is set
            physicianResultList = physicianService.searchByNameFieldOfActivityLocation(fieldOfActivity, name, postalCode);
        } else if (isSet(name) && postalCode.isSet() && !fieldOfActivity.isSet()) { // only fieldOfActivity not set
            physicianResultList = physicianService.searchByNameAndLocation(name, postalCode);
        } else if (!isSet(name) && postalCode.isSet() && fieldOfActivity.isSet()) { // only name not set
            physicianResultList = physicianService.searchByFieldOfActivityAndLocation(fieldOfActivity, postalCode);
        } else if (postalCode.isSet() && !isSet(name) && !fieldOfActivity.isSet()) { // only postal code
            physicianResultList = physicianService.searchByPostalCode(postalCode.getPostalCode());
        } else if (isSet(name) && !postalCode.isSet() && !fieldOfActivity.isSet()) { // onyl
            physicianResultList = physicianService.searchByName(name);
        }
        return Resources.PAGE_PATIENT_HOME;
    }

    public ConnectedUserBean getConnectedUserBean() {
        return connectedUserBean;
    }

    public void setConnectedUserBean(ConnectedUserBean connectedUserBean) {
        this.connectedUserBean = connectedUserBean;
    }


    private boolean isSet(String s) {
        return s != null && !s.isEmpty();
    }

    public String validateAppointment(Physician selectedPhysician, int appointmentId) {
        Preconditions.checkArgument(appointmentId >= 0, "The user ID sent by search is not valid");
        Patient patient = connectedUserBean.getConnectedPatient();
        PatientService patientService = new PatientService(patient);
        patientService.setNewAppointment(selectedPhysician, selectAvailabilityID, appointmentId);
        patientService.save(patient);
        return Resources.PAGE_PATIENT_PROFIL + Resources.TAG_AVOIDING_EXPIRED_VIEW;
    }


}
