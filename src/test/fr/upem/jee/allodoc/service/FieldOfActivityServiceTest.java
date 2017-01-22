package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.FieldOfActivity;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by raptao on 1/17/2017.
 */
public class FieldOfActivityServiceTest {

    @Test
    public void getByName() throws Exception {
        FieldOfActivity fieldOfActivity = new FieldOfActivity("scienceField");
        FieldOfActivityService.distinctSave(fieldOfActivity);
        Service<FieldOfActivity> service = new Service<>();
        FieldOfActivity retrievedFieldOfActivity = service.findByLongId(FieldOfActivity.class, 1L);
        assertNotNull(retrievedFieldOfActivity);
        assertEquals(fieldOfActivity.getName(), retrievedFieldOfActivity.getName());
        service.remove(retrievedFieldOfActivity);
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

}