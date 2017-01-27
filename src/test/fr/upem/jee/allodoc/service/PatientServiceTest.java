package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.Appointment;
import fr.upem.jee.allodoc.entity.Availability;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by raptao on 12/22/2016.
 */
public class PatientServiceTest {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    @Test(expected= NoResultException.class)
    public void getFromId(){
        Patient patient = new Patient();
        PatientService controller = new PatientService(patient);
        patient.setLastName("raptao");
        patient.setFirstName("thierry");
        controller.save(patient);

        Patient fromId = PatientService.getById(1L);
        assertEquals(patient.getFirstName(), fromId.getFirstName());
        controller.remove(fromId);
        PatientService.getById(1L);
    }

    @Test
    public void getAppointments() throws ParseException {
        Patient patient = new Patient();
        PatientService controller = new PatientService(patient);
        patient.setLastName("raptao");
        patient.setFirstName("thierry");
//        Patient.setId(2L);
        patient.addAppointment(new Appointment(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        patient.addAppointment(new Appointment(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save(patient);

        Patient p = PatientService.getById(patient.getId());
        assertFalse(p.getAppointments().isEmpty());
        assertEquals(2, p.getAppointments().size());

    }



    @Test
    public void setAppointmentTest() throws ParseException {
        PhysicianService phController = new PhysicianService();
        Physician physician = new Physician();
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        phController.save(physician);
        Patient patient = new Patient();
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