package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.FieldOfActivity;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by raptao on 1/17/2017.
 */
public class FieldOfActivityControllerTest {

    @Test
    public void getByName() throws Exception {
        FieldOfActivity fieldOfActivity = new FieldOfActivity("scienceField");
        Controller<FieldOfActivity> controller = new Controller<>();
        controller.save(fieldOfActivity);

        FieldOfActivity fieldOfActivity1 = controller.manager().getEntityManager().find(FieldOfActivity.class, 1L);
        assertNotNull(fieldOfActivity1);
    }

    @Test
    public void getAll() throws Exception {

    }

}