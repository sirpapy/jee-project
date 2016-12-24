package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;

/**
 * Created by raptao on 12/14/2016.
 */
public class Controller<T> {

    @Inject
    private DatabaseManager manager;

    public Controller() {
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

    public static <R> R getController(Class<R> controller) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        return container.instance().select(controller).get();
    }
}
