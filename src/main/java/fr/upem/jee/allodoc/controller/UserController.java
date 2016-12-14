package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public User authenticate(String email, String password ){
        Preconditions.checkNotNull(email);
        Preconditions.checkNotNull(password);
        TypedQuery<User> authenticateUser = manager.getEntityManager().createNamedQuery("authenticateUser", User.class);
        authenticateUser.setParameter("userEmail", email);
        authenticateUser.setParameter("userPassword", password);
        List<User> resultList = authenticateUser.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
