package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.controller.FieldOfActivityController;
import fr.upem.jee.allodoc.controller.PatientController;
import fr.upem.jee.allodoc.controller.PhysicianController;
import fr.upem.jee.allodoc.entity.*;
import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raptao on 1/10/2017.
 */
@ManagedBean
@SessionScoped
public class RegisterBean implements Serializable {

    public static final String TYPE_PHYSICIAN = "PHYSICIAN";
    public static final String TYPE_PATIENT = "PATIENT";

    private String type;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    private Address address;
    private PatientController patientController;
    private PhysicianController physicianController;

    private Location selectedPracticeArea;
    private List<Location> allPracticeAreas;

    private String status;
    private FieldOfActivity selectedFieldOfActivity;
    private List<FieldOfActivity> fieldOfActivities;

    public RegisterBean() {
        patientController = new PatientController();
        physicianController = new PhysicianController();
//        fieldOfActivities = FieldOfActivityController.getAll();
        loadFakeData();
    }

    public Location getSelectedPracticeArea() {
        return selectedPracticeArea;
    }

    public void setSelectedPracticeArea(Location selectedPracticeArea) {
        this.selectedPracticeArea = selectedPracticeArea;
    }

    public List<Location> getAllPracticeAreas() {
        return allPracticeAreas;
    }

    public void setAllPracticeAreas(List<Location> allPracticeAreas) {
        this.allPracticeAreas = allPracticeAreas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FieldOfActivity> getFieldOfActivities() {
        return fieldOfActivities;
    }

    public void setFieldOfActivities(List<FieldOfActivity> fieldOfActivities) {
        this.fieldOfActivities = fieldOfActivities;
    }

    public FieldOfActivity getSelectedFieldOfActivity() {
        return selectedFieldOfActivity;
    }

    public void setSelectedFieldOfActivity(FieldOfActivity selectedFieldOfActivity) {
        this.selectedFieldOfActivity = selectedFieldOfActivity;
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
                .setEmail(email)
                .setPassword(password)
                .build();
        patientController.takeControl(p);
        patientController.save();
        return Pages.PAGE_LOGIN_FORM + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }

    public String registerAsPhysician() {
        Physician physician = new Physician.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setBirthDate(birthDate)
                .setAddress(address)
                .setPassword(password)
                .setFieldOfActivity(selectedFieldOfActivity)
                .setPracticeArea(selectedPracticeArea)
                .setStatus(status)
                .build();
        physicianController.takeControl(physician);
        physicianController.save();
        return Pages.PAGE_LOGIN_FORM + Pages.TAG_AVOIDING_EXPIRED_VIEW;
    }


    /**
     * loads data from database
     */
    private void loadData() {
        fieldOfActivities = FieldOfActivityController.getAll();
    }

    private void loadFakeData() {
        fieldOfActivities = new ArrayList<>();
        fieldOfActivities.add(new FieldOfActivity("science field"));
        fieldOfActivities.add(new FieldOfActivity("dentist field"));
        fieldOfActivities.add(new FieldOfActivity("orthopedist field"));
        fieldOfActivities.add(new FieldOfActivity("sexophonist lol  field"));
        allPracticeAreas = new ArrayList<>();
        allPracticeAreas.add(new Location.Builder().setCity("MONTFERMEIL").setPostalCode(93370).build());
        allPracticeAreas.add(new Location.Builder().setCity("PARIS").setPostalCode(75000).build());
        allPracticeAreas.add(new Location.Builder().setCity("NICE").setPostalCode(234234).build());
        allPracticeAreas.add(new Location.Builder().setCity("LONDRE").setPostalCode(23423).build());
        allPracticeAreas.add(new Location.Builder().setCity("MARSEILLE").setPostalCode(01000).build());
    }
}
