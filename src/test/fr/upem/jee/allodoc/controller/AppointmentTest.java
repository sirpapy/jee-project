package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Appointment;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.jpa.Physician;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertTrue;

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
        PatientController paController = new PatientController();
        PhysicianController phController = new PhysicianController();

        Physician physician = new Physician();
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        phController.save(physician);


        Patient pa = new Patient();
        assertTrue(pa.getAppointments().size() == 0);

        AppointmentsController apC = new AppointmentsController();

        apC.setAppointment(pa, physician, 2);
        paController.save(pa);
        assertTrue(pa.getAppointments().size() == 1);

    }


}