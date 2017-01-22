package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.Availability;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 12/22/2016.
 */
public class PhysicianServiceTest {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    @Test
    public void getFromId(){
        Physician physician = new Physician();
        PhysicianService controller = new PhysicianService(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        controller.save();

        Physician fromId = PhysicianService.getById(1L);
        assertNotNull(fromId);
        assertEquals(physician.getFirstName(), fromId.getFirstName());

        controller.remove(fromId);
        assertNull(PhysicianService.getById(1L));
    }

    @Test
    public void register(){
        Physician physician = new Physician.Builder()
                .setEmail("email")
                .setPassword("password")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setFieldOfActivity(new FieldOfActivity("science field"))
                .setBirthDate(Date.valueOf("1992-09-28"))
                .setPracticeArea(new Location(93, "sd"))
                .setStatus("public").build();
        PhysicianService service = new PhysicianService(physician);
        service.save();

        List<Physician> search = service.search("firstName", "lastName");
        assertFalse(search.isEmpty());
        assertEquals(1, search.size());

        Physician retrieved = search.get(0);
        assertEquals(retrieved.getFirstName(), physician.getFirstName());
        assertEquals(retrieved.getLastName(), physician.getLastName());
        assertEquals(retrieved.getEmail(), physician.getEmail());
        assertEquals(retrieved.getPassword(), physician.getPassword());
        assertEquals(retrieved.getStatus(), physician.getStatus());
        assertEquals(retrieved.getBirthDate(), physician.getBirthDate());
    }
    @Test
    public void getAvailabilities() throws ParseException {
        Physician physician = new Physician();
        PhysicianService controller = new PhysicianService(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        List<Physician> search = new PhysicianService(new Physician()).search("thierry", "raptao");
        assertFalse(search.isEmpty());
        Physician physician1 = search.get(0);
        assertEquals(2, physician1.getAvailabilities().size());
//        Collection<Availability> availabilities = controller.getAvailabilities();
//        assertEquals(2, availabilities.size());
    }


    @Test
    public void searchByName() throws ParseException {
        Physician physician = new Physician();
        PhysicianService controller = new PhysicianService(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        PhysicianService searchController = new PhysicianService();
        assertEquals(searchController.searchByName("raptao").size(),1);
    }
    @Test
    public void searchByFieldOfActivity() throws ParseException {
        Physician physician = new Physician();
        PhysicianService controller = new PhysicianService(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        FieldOfActivityService fieldOfActivityService = new FieldOfActivityService();
        FieldOfActivity fieldOfActivity = new FieldOfActivity("GENERALISTE");
        fieldOfActivityService.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        PhysicianService searchController = new PhysicianService();
        assertEquals(searchController.searchByFieldOfActivity("GENERALISTE").size(),1);

    }




    @Test
    public void searchByFieldOfActivityNameLocation() throws ParseException {
        Physician physician = new Physician();
        PhysicianService controller = new PhysicianService(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        FieldOfActivityService fieldOfActivityService = new FieldOfActivityService();
        FieldOfActivity fieldOfActivity = new FieldOfActivity("GENERALISTE");
        fieldOfActivityService.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        LocationService locationService = new LocationService();
        locationService.add(75020, "Paris", "France");
        Location location = locationService.getByPostalCode(75020).get();
        physician.setPracticeArea(location);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));

        controller.save();

        PhysicianService searchController = new PhysicianService();
        assertEquals(searchController.searchByNameFieldOfActivityLocation(fieldOfActivity, "GENERALISTE", location).size(),1);

    }


}