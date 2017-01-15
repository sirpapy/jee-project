package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.controller.AppointmentsController;
import fr.upem.jee.allodoc.controller.AvailabilityController;
import fr.upem.jee.allodoc.controller.PatientController;
import fr.upem.jee.allodoc.controller.PhysicianController;
import fr.upem.jee.allodoc.jpa.Appointment;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.jpa.Physician;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Pape on 17/13/2016.
 */
public class AppointmentTest {
    private Appointment a1;
    private Appointment a2;
    private final Patient u;
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    public AppointmentTest() throws IOException, ParseException {
        u = new Patient();
        a1 = new Appointment(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30"));
        a2 = new Appointment(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45"));
    }


    @Test
    public void setAppointmentTest() throws ParseException {
        PatientController controller = new PatientController();

        Physician ph = new Physician();
        Availability availability = new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30"));
        Availability availability2 = new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45"));
        availability.setId((long) 1);
        availability2.setId((long) 2);
        AvailabilityController avC = new AvailabilityController();
        avC.save(availability);
        avC.save(availability2);
        ph.setAvailability(availability);
        ph.setAvailability(availability2);

        PhysicianController phC = new PhysicianController();
        phC.save(ph);
        Patient pa = new Patient();
        assertTrue(pa.getAppointments().size() == 0);

        AppointmentsController apC = new AppointmentsController();

        apC.setAppointment(pa, ph, availability.getId());
        controller.save(pa);
        assertTrue(pa.getAppointments().size() == 1);

    }


}