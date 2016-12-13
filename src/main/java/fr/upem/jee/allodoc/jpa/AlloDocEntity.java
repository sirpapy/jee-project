package fr.upem.jee.allodoc.jpa;

import fr.upem.jee.allodoc.DatabaseManager;

import java.io.IOException;

/**
 * Created by raptao on 12/13/2016.
 */
abstract class AlloDocEntity<T> {

    private final DatabaseManager<T> manager;

    public AlloDocEntity() throws IOException {
        this.manager = DatabaseManager.getManager();
    }

    public DatabaseManager<T> manager() {
        return manager;
    }

    /**
     * Saves or update this {@link AlloDocEntity} in the AlloDoc database
     */
    public abstract  void save();

    /**
     * Removes this {@link AlloDocEntity} from the AlloDoc database
     */
    public abstract void remove();
}
