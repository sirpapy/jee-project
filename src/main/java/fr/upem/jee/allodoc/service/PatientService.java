package fr.upem.jee.allodoc.service;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.*;


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;


/**
 * Created by raptao on 12/14/2016.
 */
public class PatientService extends UserServiceImpl<Patient> {

    private Patient patient;

    public PatientService(Patient patient) {
        super();
        this.patient = patient;
    }

    public PatientService() {
        super();
    }

    public static Patient getById(long patientId){
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        return  databaseManager.findByLongId(Patient.class, patientId);

    }


    public boolean setNewAppointment(Physician physician, long availabilityId, long appointmentId) {
        Preconditions.checkNotNull(physician);
        Preconditions.checkArgument(availabilityId >= 0);
        Preconditions.checkArgument(appointmentId >= 0);
        PhysicianService physicianService = new PhysicianService(physician);
        if (!physicianService.isAvailableAt(availabilityId)) {
            Query query = manager().getEntityManager().createNativeQuery("update physician_availability set appointment_id = " + appointmentId + " WHERE physician_id =" + physician.getId() + " AND availability_id = " + availabilityId + "");
            query.getFirstResult();
            Availability appointment = manager().getEntityManager().find(Availability.class, appointmentId);
            patient.addAppointment(new Appointment(appointment.getBeginAvailability(), appointment.getEndAvailability()));
            return true;
        }
        return false;
    }

    /**
     * This methods takes control of a new {@link Patient} object.
     *
     * @param patient the {@link Patient} to be taken control of
     */
    public void takeControl(Patient patient) {
        Objects.requireNonNull(patient);
        this.patient = patient;
    }


//    public Patient getFromId(Long id) {
//        Preconditions.checkArgument(id >= 0, "ID must be greater than 0");
//        TypedQuery<Patient> query = manager().getEntityManager().createNamedQuery("getPatientFromId", Patient.class);
//        query.setParameter("pId", id);
//        return query.getSingleResult();
//    }

    public List<Patient> search(String firstName, String lastName) {
        Preconditions.checkNotNull(firstName, "firstName should not be null");
        Preconditions.checkNotNull(lastName, "lastName should not be null");
        TypedQuery<Patient> query = manager().getEntityManager().createNamedQuery("findPatientFirstnameLastName", Patient.class);
        query.setParameter("pLastName", lastName);
        query.setParameter("pFirstName", firstName);
        return query.getResultList();
    }


    public void save() {
        super.save(patient);
    }


}
