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

        Physician physician = new Physician();
        PhysicianController phController = new PhysicianController(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");

        // TODO change Date usage : the database doesnt store the right values
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        phController.save();


        Patient patient = new Patient();
        PatientController patientController = new PatientController(patient);
        AppointmentController appointmentController = new AppointmentController();

        appointmentController.setAppointment(patient, physician, 2);
        patientController.save();
        assertTrue(patient.getAppointments().size() == 1);

    }


}