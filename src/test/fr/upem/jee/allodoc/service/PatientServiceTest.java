package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.Appointment;
import fr.upem.jee.allodoc.entity.Availability;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.sample.SampleUsers;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by raptao on 12/22/2016.
 */
public class PatientServiceTest {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    @Before
    public void clear() {
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        databaseManager.clear(Patient.class);
    }

    @Test
    public void getAppointments() throws ParseException {
        Patient patient = new Patient();
        PatientService controller = new PatientService(patient);
        patient.setLastName("raptao");
        patient.setFirstName("thierry");
        patient.addAppointment(new Appointment(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        patient.addAppointment(new Appointment(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save(patient);
        Patient p = controller.search(patient.getFirstName(), patient.getLastName()).get(0);
        assertEquals(2, p.getAppointments().size());

    }


    @Test
    public void setAppointmentTest() throws ParseException {
        Physician physician = new Physician();
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        PhysicianService phController = new PhysicianService();
        phController.takeControl(physician);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        phController.save();
        Patient patient = SampleUsers.patient();
        PatientService patientService = new PatientService(patient);
        assertTrue(patient.getAppointments().size() == 0);
        patientService.setNewAppointment(physician, physician.getAvailabilities().get(0).getId(), physician.getAvailabilities().get(0).getId());
        patientService.save();
        assertTrue(patient.getAppointments().size() == 1);

    }


    @Test
    public void isFreeAppointmentTest() throws Exception {
//        assertTrue(a1.isFree());
    }
//
//    @Test
//    public void setAppointmentTest() throws Exception {
//        u.setId(1L);
//        a1.setAppointment(u.getId());
////        assertFalse(a1.isFree());
//    }
//
//    @Test
//    public void removeAppointmentTest() throws Exception {
//        a1 = new Appointment(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30"));
//        u.setId(1L);
//        a1.setAppointment(u.getId());
//        a1.removeAppointment();
//        assertEquals(true, a1.isFree());
//    }


}