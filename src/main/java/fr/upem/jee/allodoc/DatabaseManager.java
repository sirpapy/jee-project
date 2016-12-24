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
public class DatabaseManager {

    private final EntityManager em;
    private final EntityTransaction transaction;

    public DatabaseManager() throws IOException {
        this(getApplicationMode());
    }

    public DatabaseManager(String applicationMode) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(applicationMode);
        this.em = factory.createEntityManager();
        transaction = em.getTransaction();
    }

    /**
     *
     * @return the application mode ( PRODUCTION or DEVELOPMENT )
     * @throws IOException
     */
    private static String getApplicationMode() throws IOException {
        Properties properties = System.getProperties();
        properties.load(DatabaseManager.class.getResourceAsStream("/application.properties"));
        return properties.getProperty("mode");
    }


    /**
     * Saves or updates entities in the database
     * @param entities
     */
    public void save(Object... entities) {
       applyTransaction(em::persist, entities);
    }


    /**
     * Removes entities from database
     * @param entities
     */
    public void remove( Object... entities ){
        applyTransaction(em::remove, entities);
    }

    /**
     *
     * @return the entity manager of the database
     */
    public EntityManager getEntityManager() {
        return em;
    }

    private void applyTransaction(Consumer<Object> consumer, Object...entities){
        transaction.begin();
        for(Object entity : entities){
            consumer.accept(entity);
        }
        transaction.commit();
    }
}
