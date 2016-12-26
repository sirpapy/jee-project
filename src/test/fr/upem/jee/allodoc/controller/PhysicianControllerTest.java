package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by raptao on 12/22/2016.
 */
public class PhysicianControllerTest {

    @Test
    public void getFromId(){
        PhysicianController controller = Controller.getController(PhysicianController.class);
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
    public void getAvailabilities(){
        PhysicianController controller = Controller.getController(PhysicianController.class);
        Physician physician = new Physician();
        physician.setLastName("raptao");
        physician.setFirstName("thierry");

        physician.setAvailability(new Availability(new Date()));
        controller.save(physician);

//        List<Physician> search = controller.search("thierry", "raptao");
//        assertFalse(search.isEmpty());
    }



}