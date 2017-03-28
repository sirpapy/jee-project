package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.Appointment;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.SearchItem;
import fr.upem.jee.allodoc.sample.SampleUsers;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

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
//        Physician physician = SampleUsers.physician();
//        PhysicianService phController = new PhysicianService();
//        phController.takeControl(physician);
//        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
//        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
//        phController.save();
//        Patient patient = SampleUsers.patient();
//        PatientService patientService = new PatientService(patient);
////        assertTrue(patient.getAppointments().size() == 0);
//        patientService.setNewAppointment(physician, physician.getAvailabilities().get(0).getId(), physician.getAvailabilities().get(0).getId());
//        patientService.save();
//        assertTrue(patient.getAppointments().size() == 1);

    }


    @Test
    public void getSearchItems() {
        assertTrue(PatientService.getAll().isEmpty());
        Patient p = SampleUsers.patient();
        SearchItem build = SearchItem.builder()
                .setFieldOfActivity("field")
                .setPhysicianName("physician")
                .setPostalCode(93)
                .build();
        p.addSearchItem(build);
        PatientService patientService = new PatientService(p);
        patientService.save();

        List<Patient> all = PatientService.getAll();
        assertEquals(1, all.size());

        Patient gotten = all.get(0);
        SearchItem item = SearchItem.builder()
                .setFieldOfActivity("science")
                .setPhysicianName("scientist")
                .setPostalCode(93)
                .build();
        gotten.addSearchItem(item);
        patientService.takeControl(gotten);
        patientService.save();

        assertEquals(1, PatientService.getAll().size());


    }


}