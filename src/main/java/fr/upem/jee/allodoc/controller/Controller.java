package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;

import java.io.IOException;

/**
 * Created by raptao on 12/14/2016.
 */
public class Controller<T> {

    private DatabaseManager manager;

    public Controller() {
        try {
            manager = new DatabaseManager();
        } catch (IOException e) {
            throw new AssertionError("Cannot instantiate controller",e);
        }
    }

    public DatabaseManager manager() {
        return manager;
    }

    public void save(T object) {
        manager.save(object);
    }

    public void remove(T object) {
        manager.remove(object);
    }

}
