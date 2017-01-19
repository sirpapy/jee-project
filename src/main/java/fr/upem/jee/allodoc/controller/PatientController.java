package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.jpa.Appointment;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.jpa.Physician;
import fr.upem.jee.allodoc.DatabaseManager;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;


/**
 * Created by raptao on 12/14/2016.
 */
public class PatientController extends UserController {

    private Patient patient;

    public PatientController(Patient patient) {
        super();
        this.patient = patient;
    }

    public PatientController() {
        super();
    }


//    PatientController patientController = new PatientController(patient);
//    PhysicianController physicianController = new PhysicianController(physician);
//
//    Appointment appointment;
//    Optional<Availability> avs = physicianController.getAvailabilities().stream().filter(e -> e.getId() == idAppointment).findFirst();
//        if (avs.isPresent()) {
//        appointment = new Appointment(avs.get().getBeginAvailability(), avs.get().getEndAvailability());
////            physician.validateAppointment(idAppointment);
//        patient.addAppointment(appointment);
//        patientController.save(patient);
//        return true;
//
//    }
//        return false;
//


    public boolean setNewAppointment(Physician physician, long availabilityId, long appointmentId) {
        Preconditions.checkNotNull(physician);
        Preconditions.checkArgument(availabilityId >= 0);
        Preconditions.checkArgument(appointmentId >= 0);
        PhysicianController physicianController = new PhysicianController(physician);
        if (!physicianController.isAvailableAt(availabilityId)) {

            // TODO complete section
            // taper sur la table physician_appointment
            // update physician_availability set appointment_id = appointmentId
            // where physician_id = physician.getId() and availability_id = availabilityId

            Query query = manager().getEntityManager().createNativeQuery("update physician_availability set appointment_id = " + appointmentId + " WHERE physician_id =" + physician.getId() + " AND availability_id = " + availabilityId + "");
            query.getFirstResult();
            Availability appointment = DatabaseManager.getDatabaseManager().getEntityManager().find(Availability.class, appointmentId);
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


    public Patient getFromId(Long id) {
        Preconditions.checkArgument(id >= 0, "ID must be greater than 0");
        TypedQuery<Patient> query = manager().getEntityManager().createNamedQuery("getPatientnFromId", Patient.class);
        query.setParameter("pId", id);
        return query.getSingleResult();
    }

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
