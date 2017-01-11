package fr.upem.jee.allodoc.jpa;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@NamedQueries({

        @NamedQuery(name = "getPhysicianFromId",
                query = "SELECT p from Physician p where p.id = :pId"),

        @NamedQuery(name = "findPhysicianFirstnameLastName",
                query = "SELECT p from Physician p where p.firstName = :pFirstName and p.lastName = :pLastName"),

})

public class Physician extends User implements Serializable {

    private String dateAccreditation;
    private String regionExercice;
    private String nomOAAMedecin;
    private String nomDepartement;
    private String finess;
    private String status;

    @OneToOne
    private FieldOfActivity fieldOfActivity;

    @OneToOne
    private Location practiceArea;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "physician_id"), inverseJoinColumns = @JoinColumn(name = "availability_id"))
    private List<Availability> availabilities;

    public Physician() {

    }

    private Physician(String firstName, String lastName, FieldOfActivity fieldOfActivity, String dateAccreditation, String nomOAAMedecin, String nomDepartement, String regionExercice, String finess, String status) {
        super(firstName, lastName,null, null, null, null);
        this.fieldOfActivity = Objects.requireNonNull(fieldOfActivity);
        this.dateAccreditation = Objects.requireNonNull(dateAccreditation);
        this.nomOAAMedecin = Objects.requireNonNull(nomOAAMedecin);
        this.nomDepartement = Objects.requireNonNull(nomDepartement);
        this.regionExercice = Objects.requireNonNull(regionExercice);
        this.finess = Objects.requireNonNull(finess);
        this.status = Objects.requireNonNull(status);
    }


    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
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

    public Location getPracticeArea() {
        return practiceArea;
    }

    public void setPracticeArea(Location practiceArea) {
        this.practiceArea = practiceArea;
    }

    public void setAvailability(Availability availability) {
        Preconditions.checkNotNull(availability);
        if (this.availabilities == null) {
            this.availabilities = new ArrayList<>();
        }
        this.availabilities.add(availability);
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


    public static class Builder {
        private String firstName;
        private String lastName;
        private FieldOfActivity fieldOfActivity;
        private String dateAccreditation;
        private String nomOAAMedecin;
        private String nomDepartement;
        private String regionExercice;
        private String finess;
        private String status;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setFieldOfActivity(FieldOfActivity fieldOfActivity) {
            this.fieldOfActivity = fieldOfActivity;
            return this;
        }

        public Builder setDateAccreditation(String dateAccreditation) {
            this.dateAccreditation = dateAccreditation;
            return this;
        }

        public Builder setNomOAAMedecin(String nomOAAMedecin) {
            this.nomOAAMedecin = nomOAAMedecin;
            return this;
        }

        public Builder setNomDepartement(String nomDepartement) {
            this.nomDepartement = nomDepartement;
            return this;
        }

        public Builder setRegionExercice(String regionExercice) {
            this.regionExercice = regionExercice;
            return this;
        }

        public Builder setFiness(String finess) {
            this.finess = finess;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Physician build() {
            return new Physician(firstName, lastName, fieldOfActivity, dateAccreditation, nomOAAMedecin, nomDepartement, regionExercice, finess, status);
        }
    }

}
