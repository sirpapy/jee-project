package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.DatabaseManager;

/**
 * Created by raptao on 12/14/2016.
 */
public class Service<T> {

    private DatabaseManager manager;

    public Service() {
        manager = DatabaseManager.getDatabaseManager();
    }

    public DatabaseManager manager() {
        return manager;
    }

    public void save(T object) {
        manager.saveOrUpdate(object);
        manager.getEntityManager().close();
    }

    public void remove(T object) {
        manager.remove(object);
    }

    public T findByLongId(Class<T> className, long id) {
        return manager.findByLongId(className, id);
    }

    public void closeSession() {
        if (!manager.getEntityManager().isOpen()) {
            throw new IllegalStateException("Session is already closed");
        }
        manager.getEntityManager().close();
    }
}
