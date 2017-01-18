package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.FieldOfActivity;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by raptao on 1/17/2017.
 */
public class FieldOfActivityControllerTest {

    @Test
    public void getByName() throws Exception {
        FieldOfActivity fieldOfActivity = new FieldOfActivity("scienceField");
        FieldOfActivityController.distinctSave(fieldOfActivity);
        Controller<FieldOfActivity> controller = new Controller<>();
        FieldOfActivity retrievedFieldOfActivity = controller.findByLongId(FieldOfActivity.class, 1L);
        assertNotNull(retrievedFieldOfActivity);
        assertEquals(fieldOfActivity.getName(), retrievedFieldOfActivity.getName());
        controller.remove(retrievedFieldOfActivity);
    }

    @Test
    public void getAll() throws Exception {
        FieldOfActivity scienceField = new FieldOfActivity("scienceField");
        FieldOfActivity dentistField = new FieldOfActivity("dentistField");
        FieldOfActivityController.distinctSave(scienceField);
        FieldOfActivityController.distinctSave(dentistField);
        List<FieldOfActivity> all = FieldOfActivityController.getAll();
        assertEquals(2, all.size());


    }

}