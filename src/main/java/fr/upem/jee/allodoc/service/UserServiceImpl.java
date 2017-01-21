package fr.upem.jee.allodoc.service;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.User;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by raptao on 12/13/2016.
 */
abstract class UserServiceImpl<U extends User> extends Service<U> implements UserService<U>{

    public UserServiceImpl(){
        super();
    }

    /**
     *
     * @param email
     * @param password
     * @return the authenticated {@link User}
     */
    @Override
    public Optional<User> authenticate(String email, String password){
        Preconditions.checkNotNull(email);
        Preconditions.checkNotNull(password);
        TypedQuery<User> authenticateUser = manager().getEntityManager().createNamedQuery("getAuthenticatedUser", User.class);
        authenticateUser.setParameter("userEmail", email);
        authenticateUser.setParameter("userPassword", password);
        List<User> resultList = authenticateUser.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

}