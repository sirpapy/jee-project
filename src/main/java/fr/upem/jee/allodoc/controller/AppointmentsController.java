package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.*;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Sirpapy on 11/01/2017.
 */
public class AppointmentsController extends Controller<Appointment> {
    DatabaseManager manager = new DatabaseManager("DEV-MODE");

    public AppointmentsController() {
        super();
    }

    public Appointment getNewAppointmentByID(long id) {
        Preconditions.checkArgument(id>=0);
        TypedQuery<Appointment> findByID = manager.getEntityManager().createNamedQuery("findByID", Appointment.class);
        findByID.setParameter("id", id);
        List<Appointment> resultList = findByID.getResultList();
        Appointment res = resultList.isEmpty() ? null : resultList.get(0);
        return res == null ? null : new Appointment(res.getBeginHour(), res.getEndHour());

    }

    public boolean setAppointment(Patient patient, Physician physician, long idAppointment) {
        Preconditions.checkNotNull(patient);
        Preconditions.checkNotNull(physician);
        Preconditions.checkArgument(idAppointment>=0);
            PatientController pc = new PatientController();
            PhysicianController phC = new PhysicianController();

            Appointment nAp;
            Optional<Availability> avs = phC.getAvailabilities(physician).stream().filter(e -> e.getId() == idAppointment).findFirst();
            if (avs.isPresent()) {
            nAp = new Appointment(avs.get().getBeginAvailability(),avs.get().getEndAvailability());
            physician.validateAppointment(idAppointment);
            patient.addAppointment(nAp);
            pc.save(patient);
            return true;

        }
        return false;
    }


}
