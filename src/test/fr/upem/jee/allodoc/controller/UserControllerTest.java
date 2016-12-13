package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserControllerTest {
    @Test
    public void save() throws Exception {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        UserController controller = container.instance().select(UserController.class).get();
        User u = new User();
        u.setFirstName("thierry");
        u.setLastName("rabearijao");
        u.setEmail("thierryrabearijao@mail.com");
        controller.save(u);
    }

}