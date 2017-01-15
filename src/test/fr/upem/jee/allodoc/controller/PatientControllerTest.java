package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Appointment;
import fr.upem.jee.allodoc.jpa.Patient;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 12/22/2016.
 */
public class PatientControllerTest {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    @Test
    public void getFromId(){
        Patient patient = new Patient();
        PatientController controller = new PatientController(patient);
        patient.setLastName("raptao");
        patient.setFirstName("thierry");
        controller.save(patient);

        Patient fromId = controller.getFromId(1L);
        assertEquals(patient.getFirstName(), fromId.getFirstName());
        controller.remove(fromId);
        assertNull(controller.getFromId(1L));
    }

    @Test
    public void getAppointments() throws ParseException {
        Patient patient = new Patient();
        PatientController controller = new PatientController(patient);
        patient.setLastName("rapthhhho");
        patient.setFirstName("thieyyyrry");
//        Patient.setId(2L);
        patient.addAppointment(new Appointment(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        patient.addAppointment(new Appointment(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save(patient);

        Patient p = controller.getFromId(patient.getId());
        assertFalse(p.getAppointments().isEmpty());
        assertEquals(2, p.getAppointments().size());
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