package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.service.LocationService;
import fr.upem.jee.allodoc.service.PhysicianService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.IOException;

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
    }
}
