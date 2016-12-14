package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.User;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserController{

    @Inject
    private DatabaseManager manager;

    public UserController() {
    }

    public void save(User user){
        manager.save(user);
    }

    public void remove(User user){
        manager.remove(user);
    }

    /**
     *
     * @param email
     * @param password
     * @return the authenticated {@link User}
     */
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
