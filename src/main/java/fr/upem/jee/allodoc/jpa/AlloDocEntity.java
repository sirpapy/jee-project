package fr.upem.jee.allodoc.jpa;

/**
 * Created by raptao on 12/13/2016.
 */
public interface AlloDocEntity {

    /**
     * Saves or update this {@link AlloDocEntity} in the AlloDoc database
     */
    public void save();

    /**
     * Removes this {@link AlloDocEntity} from the AlloDoc database
     */
    public void remove();
}
