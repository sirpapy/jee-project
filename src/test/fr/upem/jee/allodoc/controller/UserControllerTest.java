package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.User;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserControllerTest {
    @Test
    public void controllerTest() throws Exception {
        UserController controller = new UserController();
        User u = new User();
        u.setFirstName("thierry");
        u.setLastName("rabearijao");
        u.setEmail("thierryrabearijao@mail.com");
        u.setPassword("password");
        controller.save(u);
        Optional<User> authenticate = controller.authenticate("thierryrabearijao@mail.com", "password");
        assertTrue( authenticate.isPresent());
        controller.remove(u);
    }

}