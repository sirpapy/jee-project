package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.*;

import javax.persistence.TypedQuery;
import java.util.List;
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

        TypedQuery<Appointment> findByID = manager.getEntityManager().createNamedQuery("findByID", Appointment.class);
        findByID.setParameter("id", id);
        List<Appointment> resultList = findByID.getResultList();
        Appointment res = resultList.isEmpty() ? null : resultList.get(0);
        return res == null ? null : new Appointment(res.getBeginHour(), res.getEndHour());

    }

    public boolean setAppointment(Patient patient, Physician physician, long idAppointment) {
        PatientController pc = new PatientController();
        AppointmentsController apc = new AppointmentsController();
        Appointment nAp;
        List<Availability> avs = physician.getAvailabilities().stream().filter(e -> e.getId() == idAppointment).collect(Collectors.toList());
        if (avs.size() != 0) {
            nAp = getNewAppointmentByID(idAppointment);
            if (nAp == null) {
                return false;
            }
            physician.validateAppointment(idAppointment);
            patient.addAppointment(nAp);
            pc.save(patient);
            return true;

        }
        return false;
    }


}
