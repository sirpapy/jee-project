package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.Location;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertFalse;

/**
 * Created by raptao on 1/23/2017.
 */
public class LocationServiceTest {
    @Test
    public void fillWithLocations() throws IOException {
        LocationService.fillDatabaseWithLocations();
        List<Location> all = LocationService.getAll();
        assertFalse(all.isEmpty());
    }

}