package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 12/22/2016.
 */
public class PhysicianControllerTest {

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
    public void getAvailabilities(){
        Physician physician = new Physician();
        PhysicianController controller = new PhysicianController(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");

        physician.setAvailability(new Availability(new Date()));
        physician.setAvailability(new Availability(new Date()));
        controller.save();

        List<Physician> search = new PhysicianController(new Physician()).search("thierry", "raptao");
        assertFalse(search.isEmpty());
        Physician physician1 = search.get(0);
        Collection<Availability> availabilities = controller.getAvailabilities();
        assertEquals(2, availabilities.size());
    }




}