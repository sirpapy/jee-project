package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.User;

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

    public void save(User user){
        manager.save(user);
    }

    public Collection<User> find( ){
        manager.getEntityManager().find(User.class, 1L);
        return null;
    }
}
