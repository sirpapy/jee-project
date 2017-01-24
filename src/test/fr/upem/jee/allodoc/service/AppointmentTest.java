package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.Appointment;
import fr.upem.jee.allodoc.entity.Patient;

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

//
//    @Test
//    public void setAppointmentTest() throws ParseException {
//        PhysicianService phController = new PhysicianService();
//
//        Physician physician = new Physician();
//        physician.setLastName("raptao");
//        physician.setFirstName("thierry");
//        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
//        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
//        phController.saveOrUpdate(physician);
//
//
//        Patient patient = new Patient();
//        PatientService patientController = new PatientService(patient);
//        assertTrue(patient.getAppointments().size() == 0);
//
//        AppointmentService appointmentController = new AppointmentService();
//
//        appointmentController.setAppointment(patient, physician, 2);
//        patientController.saveOrUpdate();
//        assertTrue(patient.getAppointments().size() == 1);
//
//    }


}