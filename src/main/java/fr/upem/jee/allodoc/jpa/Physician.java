package fr.upem.jee.allodoc.jpa;

import javax.persistence.OneToOne;
import java.lang.reflect.Field;
import java.util.Objects;

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
        this.fieldOfActivity = Objects.requireNonNull(fieldOfActivity);
        this.dateAccreditation = Objects.requireNonNull(dateAccreditation);
        this.nomOAAMedecin = Objects.requireNonNull(nomOAAMedecin);
        this.nomDepartement = Objects.requireNonNull(nomDepartement);
        this.regionExercice = Objects.requireNonNull(regionExercice);
        this.finess = Objects.requireNonNull(finess);
        this.status = Objects.requireNonNull(status);
    }

    public Physician(String firstName, String lastName) {
        super(firstName, lastName);
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
