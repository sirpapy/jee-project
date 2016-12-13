package fr.upem.jee.allodoc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * Created by raptao on 12/13/2016.
 */
public class DatabaseManager<T> {

    private final EntityManager em;
    private final EntityTransaction transaction;

    public DatabaseManager(String applicationMode) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(applicationMode);
        this.em = factory.createEntityManager();
        transaction = em.getTransaction();
    }

    /**
     * Returns a newly created {@link DatabaseManager} object.
     * The returned object depends on the value of the property mode in the file "application.properties"
     *
     * @return DatabaseManager
     * @throws IOException in case of I/O errors
     */
    public static <R> DatabaseManager<R> getManager() throws IOException {
        Properties properties = System.getProperties();
        properties.load(DatabaseManager.class.getResourceAsStream("/application.properties"));
        String mode = properties.getProperty("mode");
        return new DatabaseManager<>(mode);
    }


    /**
     * Saves or updates entities in the database
     * @param entities
     */
    public void save(T... entities) {
       applyTransaction(em::persist, entities);
    }


    /**
     * Removes entities from database
     * @param entities
     */
    public void remove( T... entities ){
        applyTransaction(em::remove, entities);
    }

    /**
     *
     * @return the entity manager of the database
     */
    public EntityManager getEntityManager() {
        return em;
    }

    private void applyTransaction(Consumer<T> consumer, T...entities){
        transaction.begin();
        for(T entity : entities){
            consumer.accept(entity);
        }
        transaction.commit();
    }
}
