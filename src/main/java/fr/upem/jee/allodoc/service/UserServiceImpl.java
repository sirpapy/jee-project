package fr.upem.jee.allodoc.service;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.entity.UserRole;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by raptao on 12/13/2016.
 */
abstract class UserServiceImpl<U extends User> extends Service<U> implements UserService<U> {

    private U user;

    UserServiceImpl(U user) {
        this.user = user;
    }

    public UserServiceImpl() {
        super();
    }

    U getControlledUser() {
        return user;
    }

    public void save() {
        Optional<UserRole> existingRoleByName = getExistingRoleByName();
        if( existingRoleByName.isPresent()){
            user.setRole(existingRoleByName.get());
        }
        user.setFirstName(StringUtils.capitalize(user.getFirstName().toLowerCase()));
        user.setLastName(user.getLastName().toUpperCase());
        manager().saveOrUpdate(user);
    }

    /**
     * This methods takes control of a new {@link User} ( or any sub class ) object.
     *
     * @param user the {@link User} to be taken control of
     */
    @Override
    public void takeControl(U user) {
        this.user = user;
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

    Optional<UserRole> getExistingRoleByName(){
        TypedQuery<UserRole> findRoleByName = manager().getEntityManager().createNamedQuery("findRoleByName", UserRole.class);
        findRoleByName.setParameter("rName", user.getRole().getName());
        List<UserRole> resultList = findRoleByName.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }
}
