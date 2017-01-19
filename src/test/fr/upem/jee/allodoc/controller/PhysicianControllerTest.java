package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Appointment;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.FieldOfActivity;
import fr.upem.jee.allodoc.jpa.Physician;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 12/22/2016.
 */
public class PhysicianControllerTest {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    @Test
    public void getFromId(){
        Physician physician = new Physician();
        PhysicianController controller = new PhysicianController(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        controller.save();

        Physician fromId = PhysicianController.getFromId(1L);
        assertNotNull(fromId);
        assertEquals(physician.getFirstName(), fromId.getFirstName());

        controller.remove(fromId);
        assertNull(PhysicianController.getFromId(1L));
    }

    @Test
    public void getAvailabilities() throws ParseException {
        Physician physician = new Physician();
        PhysicianController controller = new PhysicianController(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        List<Physician> search = new PhysicianController(new Physician()).search("thierry", "raptao");
        assertFalse(search.isEmpty());
        Physician physician1 = search.get(0);
        Collection<Availability> availabilities = controller.getAvailabilities();
        assertEquals(2, availabilities.size());
    }


    @Test
    public void searchByName() throws ParseException {
        Physician physician = new Physician();
        PhysicianController controller = new PhysicianController(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        PhysicianController searchController = new PhysicianController();
        assertEquals(searchController.searchByName("raptao").size(),1);
    }
    @Test
    public void searchByFieldOfActivity() throws ParseException {
        Physician physician = new Physician();
        PhysicianController controller = new PhysicianController(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        FieldOfActivityController fieldOfActivityController = new FieldOfActivityController();
        FieldOfActivity fieldOfActivity = new FieldOfActivity("GENERALISTE");
        fieldOfActivityController.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        PhysicianController searchController = new PhysicianController();
        assertEquals(searchController.searchByNameFieldOfActivity("GENERALISTE").size(),1);

    }




}