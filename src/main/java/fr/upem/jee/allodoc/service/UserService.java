package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.User;

import java.util.Optional;

/**
 * Created by raptao on 1/21/2017.
 */
public interface UserService<U> {
    public Optional<User> authenticate(String email, String password);

    public Optional<User> findByLogin(String login);

    public void takeControl(U user);

    public void save();
}
