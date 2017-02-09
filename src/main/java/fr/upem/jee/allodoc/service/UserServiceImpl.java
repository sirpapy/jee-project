package fr.upem.jee.allodoc.service;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by raptao on 12/13/2016.
 */
abstract class UserServiceImpl<U extends User> extends Service<U> implements UserService<U> {

    private U user;

    public UserServiceImpl() {
        super();
    }

    public UserServiceImpl(U user) {
        this.user = user;
    }

    public U getControlledUser() {
        return user;
    }

    /**
     * @param email
     * @param password
     * @return the authenticated {@link User}
     */
    @Override
    public Optional<User> authenticate(String email, String password) {
        Preconditions.checkNotNull(email);
        Preconditions.checkNotNull(password);
        TypedQuery<User> authenticateUser = manager().getEntityManager().createNamedQuery("getAuthenticatedUser", User.class);
        authenticateUser.setParameter("userEmail", email);
        authenticateUser.setParameter("userPassword", password);
        List<User> resultList = authenticateUser.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }


    @Override
    public void takeControl(U user) {
        this.user = user;
    }

    public void save() {
        System.out.println("LOGIN "+user.getAccount().getUserName());
        manager().saveOrUpdate(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        TypedQuery<User> getUserByLogin = manager().getEntityManager().createNamedQuery("getUserByLogin", User.class);
        getUserByLogin.setParameter("userEmail", login);
        List<User> resultList = getUserByLogin.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }
}
