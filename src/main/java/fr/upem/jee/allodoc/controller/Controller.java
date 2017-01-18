package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;

/**
 * Created by raptao on 12/14/2016.
 */
public class Controller<T> {

    private DatabaseManager manager;

    public Controller() {
        manager = DatabaseManager.getDatabaseManager();
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

    public T findByLongId(Class<T> className, long id){
        return manager.findByLongId(className, id);
    }
}
