package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

/**
 * Created by raptao on 12/14/2016.
 */
public class PatientController extends Controller<Patient> {

    public PatientController() {
        super();
    }


    public Patient getFromId(Long id) {
        return manager().getEntityManager().find(Patient.class, id);
    }

    public List<Patient> search(String firstName, String lastName) {
        Preconditions.checkNotNull(firstName, "firstName should not be null");
        Preconditions.checkNotNull(lastName, "lastName should not be null");
        TypedQuery<Patient> query = manager().getEntityManager().createNamedQuery("findPatientFirstnameLastName", Patient.class);
        query.setParameter("pLastName", lastName);
        query.setParameter("pFirstName", firstName);
        return query.getResultList();
    }


    public void save(Patient patient) {
        manager().save(patient);
    }




}
