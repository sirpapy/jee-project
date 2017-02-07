package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.*;
import fr.upem.jee.allodoc.sample.SampleUsers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 12/22/2016.
 */
public class PhysicianServiceTest {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    @Before
    public void clear(){
        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        manager.clear(Physician.class);
        manager.clear(User.class);
        manager.clear(FieldOfActivity.class);
        manager.clear(Address.class);
        manager.clear(Location.class);

    }
    @Test
    public void searchByFieldOfActivity1() throws Exception {

    }

    @Test
    public void searchByNameFieldOfActivityLocation() throws Exception {

    }

    @Test
    public void register() {
        Physician physician = SampleUsers.physician();
        PhysicianService service = new PhysicianService(physician);
        service.save();

        List<Physician> search = service.search(physician.getFirstName(), physician.getLastName());
        assertEquals(1, search.size());

        Physician retrieved = search.get(0);
        assertEquals(retrieved.getFirstName(), physician.getFirstName());
        assertEquals(retrieved.getLastName(), physician.getLastName());
        assertEquals(retrieved.getStatus(), physician.getStatus());
        assertEquals(retrieved.getBirthDate(), physician.getBirthDate());
    }

    @Test
    public void getAvailabilities() throws ParseException {
        Physician physician = SampleUsers.physician();
        PhysicianService physicianService = new PhysicianService(physician);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        physicianService.save();

        List<Physician> search = physicianService.search(physician.getFirstName(), physician.getLastName());
        assertFalse(search.isEmpty());
        Physician physician1 = search.get(0);
        assertEquals(2, physician1.getAvailabilities().size());
    }


    @Test
    public void distinctSave() {
        Physician physician = SampleUsers.physician();

        Physician physician2 = SampleUsers.physician();

        PhysicianService physicianService = new PhysicianService(physician);
        physicianService.save();
        physicianService.takeControl(physician2);
        physicianService.save();

        assertEquals(2, PhysicianService.getAll().size());
        assertEquals(1, FieldOfActivityService.getAll().size());
    }

    @Test
    public void searchByName() throws ParseException {
        Physician physician = SampleUsers.physician();
        PhysicianService controller = new PhysicianService(physician);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        PhysicianService searchController = new PhysicianService();
        assertEquals(searchController.searchByName(physician.getFirstName()).size(), 1);
    }

    @Test
    public void searchByFieldOfActivity() throws ParseException {
        Physician physician = new Physician();
        PhysicianService physicianService = new PhysicianService(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        Location paris = new Location.Builder().setCity("paris").build();
        physician.setPracticeArea(paris);
        physician.setAddress(new Address.Builder().setLocation(paris).build());
        FieldOfActivity fieldOfActivity = new FieldOfActivity("GENERALISTE");
        FieldOfActivityService.distinctSave(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        physicianService.save();

        PhysicianService searchController = new PhysicianService();
        assertEquals(searchController.searchByFieldOfActivity(new FieldOfActivity("GENERALISTE")).size(), 1);

    }


    @Test
    public void searchByFieldOfActivityNameLocation() throws ParseException {
        Physician physician = SampleUsers.physician();
        PhysicianService physicianService = new PhysicianService(physician);
        FieldOfActivity fieldOfActivity = new FieldOfActivity("GENERALISTE");
        physician.setFieldOfActivity(fieldOfActivity);
        LocationService locationService = new LocationService();
        locationService.save(75020, "Paris", "France");
        Location location = locationService.getByPostalCode(75020).get();
        physician.setPracticeArea(location);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));

        physicianService.save();

        PhysicianService searchController = new PhysicianService();
        assertEquals(searchController.searchByNameFieldOfActivityLocation(fieldOfActivity, "GENERALISTE", location).size(), 1);

    }

    @Test
    public void fillWithPhysicians() throws IOException {
        PhysicianService.fillDatabaseWithPhysicians();
        List<Physician> all = PhysicianService.getAll();
        assertFalse(all.isEmpty());
    }

}