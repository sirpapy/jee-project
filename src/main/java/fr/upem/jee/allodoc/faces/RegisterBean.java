package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.*;
import fr.upem.jee.allodoc.service.FieldOfActivityService;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.Optional;

/**
 * Created by raptao on 1/10/2017.
 */
@ManagedBean(eager = true)
@SessionScoped
public class RegisterBean implements Serializable {

    private String type;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private Address address;
    private PatientService patientService;
    private PhysicianService physicianService;
    private Location selectedPracticeArea;

    private String status;
    private FieldOfActivity selectedFieldOfActivity;


    public RegisterBean() {
        patientService = new PatientService();
        physicianService = new PhysicianService();
        address = new Address();
        loadData();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Location getSelectedPracticeArea() {
        return selectedPracticeArea;
    }

    public void setSelectedPracticeArea(Location selectedPracticeArea) {
        this.selectedPracticeArea = selectedPracticeArea;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public FieldOfActivity getSelectedFieldOfActivity() {
        return selectedFieldOfActivity;
    }

    public void setSelectedFieldOfActivity(FieldOfActivity selectedFieldOfActivity) {
        Optional<FieldOfActivity> byName = FieldOfActivityService.getByName(selectedFieldOfActivity.getName());
        if (byName.isPresent()) {
            this.selectedFieldOfActivity = byName.get();
        } else {
            FieldOfActivityService service = new FieldOfActivityService();
            service.save(selectedFieldOfActivity);
            this.selectedFieldOfActivity = FieldOfActivityService.getByName(selectedFieldOfActivity.getName()).get();
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    /**
     * Registers a new {@link Patient} in the database
     *
     * @return the redirection page ( login page )
     */
    public String registerAsPatient() {
        Patient p = new Patient.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setAddress(address)
                .setAccount(new Account(email, password))
                .build();
        patientService.takeControl(p);
        patientService.save();
        return Resources.PAGE_PATIENT_HOME + Resources.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public String registerAsPhysician() {
        Physician physician = new Physician.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setAccount(new Account(email, password))
                .setAddress(address)
                .setFieldOfActivity(selectedFieldOfActivity)
                .setPracticeArea(selectedPracticeArea)
                .setStatus(status)
                .build();
        physicianService.takeControl(physician);
        physicianService.save();
        return Resources.PAGE_PHYSICIAN_HOME + Resources.TAG_AVOIDING_EXPIRED_VIEW;
    }


    /**
     * loads data from database
     */
    private void loadData() {


    }
}
