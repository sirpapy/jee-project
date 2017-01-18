package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.Appointment;
import fr.upem.jee.allodoc.entity.Availability;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sirpapy on 11/01/2017.
 */
public class AppointmentController extends Controller<Appointment> {

    public AppointmentController() {
        super();
    }

    public Appointment getNewAppointmentByID(long id) {
        Preconditions.checkArgument(id >= 0);
        TypedQuery<Appointment> findByID = manager().getEntityManager().createNamedQuery("findByID", Appointment.class);
        findByID.setParameter("id", id);
        List<Appointment> resultList = findByID.getResultList();
        Appointment res = resultList.isEmpty() ? null : resultList.get(0);
        return res == null ? null : new Appointment(res.getBeginHour(), res.getEndHour());
    }

    public long getAppointmentId(Date beginHour, Date endHour){
        // TODO : complete definition
        return 0;
    }

    public boolean setAppointment(Patient patient, Physician physician, long idAppointment) {
        Preconditions.checkNotNull(patient);
        Preconditions.checkNotNull(physician);
        Preconditions.checkArgument(idAppointment >= 0);
        PatientController patientController = new PatientController(patient);
        PhysicianController physicianController = new PhysicianController(physician);

        Appointment appointment;
        // TODO fix this after removing getAvailabilities in PhysicianController
        Optional<Availability> avs = physicianController.getAvailabilities().stream().filter(e -> e.getId() == idAppointment).findFirst();
        if (avs.isPresent()) {
            appointment = new Appointment(avs.get().getBeginAvailability(), avs.get().getEndAvailability());
//            physician.validateAppointment(idAppointment);
            patient.addAppointment(appointment);
            patientController.save(patient);
            return true;
        }
        return false;
    }


}
