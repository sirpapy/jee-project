package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Appointment;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;

/**
 * Created by raptao on 12/22/2016.
 */
public class PhysicianControllerTest {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    @Test
    public void getFromId(){
        PhysicianController controller = new PhysicianController();
        Physician physician = new Physician();
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        controller.save(physician);

        Physician fromId = controller.getFromId(1L);
        assertEquals(physician.getFirstName(), fromId.getFirstName());

        controller.remove(fromId);
        assertNull(controller.getFromId(1L));
    }

    @Test
    public void getAvailabilities() throws ParseException {
        PhysicianController controller = new PhysicianController();
        Physician physician = new Physician();
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save(physician);

        List<Physician> search = controller.search("thierry", "raptao");
        assertFalse(search.isEmpty());
        Physician physician1 = search.get(0);
        Collection<Availability> availabilities = controller.getAvailabilities(physician1);
        assertEquals(2, availabilities.size());
    }




}