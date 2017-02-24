package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.Account;
import fr.upem.jee.allodoc.entity.Address;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.service.LocationService;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by raptao on 2/22/2017.
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class DatabaseInitializerBean {

    private static final int DATA_LIMIT = 15;

    public DatabaseInitializerBean() throws IOException {
        LocationService.fillDatabaseWithLocations(DATA_LIMIT);
        PhysicianService.fillDatabaseWithPhysicians(DATA_LIMIT);
        fillWithDefaultUser();
    }

    /**
     * Add a default user to the database at application initialization
     */
    private static void fillWithDefaultUser() {
        Address address = new Address.Builder()
                .setStreetName("Avenue vauquelin")
                .setStreetNumber("31 B")
                .setLocation(new Location(93370, "Montfermeil"))
                .build();
        Patient patient = new Patient.Builder()
                .setAccount(new Account("user", "user"))
                .setBirthDate(Date.valueOf("1992-09-28"))
                .setFirstName("thierry")
                .setLastName("rabearijao")
                .setAddress(address)
                .build();
        PatientService service = new PatientService(patient);
        service.save();
    }
}
