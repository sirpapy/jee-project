package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.jpa.Physician;

/**
 * Created by raptao on 1/15/2017.
 */
public class PatientController extends Controller<Patient> {

    private Patient patient;

    public PatientController(Patient patient) {
        super();
        this.patient = patient;
    }

    public boolean setNewAppointment(Physician physician, long availabilityId, long appointmentId) {
        PhysicianController physicianController = new PhysicianController(physician);
        if (physicianController.isAvailableAt(availabilityId)) {
            // TODO complete section
            // taper sur la table physician_appointment
            // update physician_availability set appointment_id = appointmentId
            // where physician_id = physician.getId() and availability_id = availabilityId


            return true;
        }
        return false;
    }


}
