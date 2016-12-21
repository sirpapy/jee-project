package fr.upem.jee.allodoc.jpa;

import javax.persistence.OneToOne;
import java.lang.reflect.Field;

/**
 * Created by raptao on 12/14/2016.
 */
public class Physician extends User {


    private String lastName;
    private String firstName;

    @OneToOne
    private FieldOfActivity fieldOfActivity;
    private String dateAccreditation;
    private String nomOAAMedecin;
    private String nomDepartement;
    private String regionExercice;
    private String finess;
    private String status;


    public Physician(String lastName, String firstName, FieldOfActivity fieldOfActivity, String dateAccreditation, String nomOAAMedecin, String nomDepartement, String regionExercice, String finess, String status) {
        super(firstName, lastName);
        this.lastName = lastName;
        this.firstName = firstName;
        this.fieldOfActivity = fieldOfActivity;
        this.dateAccreditation = dateAccreditation;
        this.nomOAAMedecin = nomOAAMedecin;
        this.nomDepartement = nomDepartement;
        this.regionExercice = regionExercice;
        this.finess = finess;
        this.status = status;
    }

    public Physician(String firstName, String lastName) {
        super(firstName, lastName);
        this.lastName = lastName;
        this.firstName = firstName;
    }
    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public FieldOfActivity getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public String getDateAccreditation() {
        return dateAccreditation;
    }

    public void setDateAccreditation(String dateAccreditation) {
        this.dateAccreditation = dateAccreditation;
    }

    public String getNomOAAMedecin() {
        return nomOAAMedecin;
    }

    public void setNomOAAMedecin(String nomOAAMedecin) {
        this.nomOAAMedecin = nomOAAMedecin;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public String getRegionExercice() {
        return regionExercice;
    }

    public void setRegionExercice(String regionExercice) {
        this.regionExercice = regionExercice;
    }

    public String getFiness() {
        return finess;
    }

    public void setFiness(String finess) {
        this.finess = finess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
