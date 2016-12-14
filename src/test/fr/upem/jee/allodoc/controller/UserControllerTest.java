package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.User;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserControllerTest {
    @Test
    public void controllerTest() throws Exception {
        UserController controller = UserController.getController();
        User u = new User();
        u.setFirstName("thierry");
        u.setLastName("rabearijao");
        u.setEmail("thierryrabearijao@mail.com");
        u.setPassword("password");
        controller.save(u);
        User authenticate = controller.authenticate("thierryrabearijao@mail.com", "password");
        assertNotNull( authenticate);
        controller.remove(u);
    }



}