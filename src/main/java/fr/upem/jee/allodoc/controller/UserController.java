package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserController {

    @Inject
    private DatabaseManager manager;

    public UserController() {
    }

    public static UserController getController(){
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        return container.instance().select(UserController.class).get();
    }

    public void save(User user){
        manager.save(user);
    }

    public void remove(User user){
        manager.remove(user);
    }
    public Collection<User> find( ){
        manager.getEntityManager().find(User.class, 1L);
        return null;
    }
}
