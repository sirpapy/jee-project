package fr.upem.jee.allodoc.controller;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * Created by raptao on 12/14/2016.
 */
public class Controller {


    public static <R> R getController(Class<R> controller) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        return container.instance().select(controller).get();
    }
}
