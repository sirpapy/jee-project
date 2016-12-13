package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.User;
import org.junit.Test;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserControllerTest {
    @Test
    public void save() throws Exception {
        UserController controller = UserController.getController();
        User u = new User();
        u.setFirstName("thierry");
        u.setLastName("rabearijao");
        u.setEmail("thierryrabearijao@mail.com");
        controller.save(u);
        controller.remove(u);
    }

}