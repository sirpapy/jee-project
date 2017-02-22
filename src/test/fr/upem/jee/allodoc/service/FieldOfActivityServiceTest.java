package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 1/17/2017.
 */
public class FieldOfActivityServiceTest {

    @Before
    public void clearFieldOfActivityTable() {
        DatabaseManager.getDatabaseManager().clear(FieldOfActivity.class);
    }

    @Test
    public void getByName() throws Exception {
        FieldOfActivity fieldOfActivity = new FieldOfActivity("scienceField");
        FieldOfActivityService.distinctSave(fieldOfActivity);
        Optional<FieldOfActivity> retrievedFieldOfActivity = FieldOfActivityService.getByName("scienceField");
        assertTrue(retrievedFieldOfActivity.isPresent());
        assertEquals(fieldOfActivity.getName(), retrievedFieldOfActivity.get().getName());
        DatabaseManager.getDatabaseManager().clear(FieldOfActivity.class);
    }

    @Test
    public void getAll() throws Exception {
        FieldOfActivity scienceField = new FieldOfActivity("scienceField");
        FieldOfActivity dentistField = new FieldOfActivity("dentistField");
        FieldOfActivityService.distinctSave(scienceField);
        FieldOfActivityService.distinctSave(dentistField);
        List<FieldOfActivity> all = FieldOfActivityService.getAll();
        assertEquals(2, all.size());
    }

    @Test
    public void distinctSave() {
        FieldOfActivity scienceField = new FieldOfActivity("scienceField");
        FieldOfActivity scienceField2 = new FieldOfActivity("scienceField");
        FieldOfActivityService.distinctSave(scienceField);
        FieldOfActivityService.distinctSave(scienceField2);
        List<FieldOfActivity> all = FieldOfActivityService.getAll();
        assertEquals(1, all.size());
    }
}