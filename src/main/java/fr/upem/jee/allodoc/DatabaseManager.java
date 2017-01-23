package fr.upem.jee.allodoc;

import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.utilities.Parser;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * Created by raptao on 12/13/2016.
 */
public class DatabaseManager {

    public static final String APPLICATION_PROPERTIES_RESOURCE = "/application.properties";
    private static EntityManagerFactory SINGLETON_FACTORY;
    private final EntityManager em;

    public DatabaseManager(String applicationMode) {
        this.em = getFactory(applicationMode).createEntityManager();
    }

    public static DatabaseManager getDatabaseManager() {
        String applicationMode = getApplicationMode();
        return new DatabaseManager(applicationMode);
    }

    private static EntityManagerFactory getFactory(String applicationMode) {
        if (SINGLETON_FACTORY == null) {
            SINGLETON_FACTORY = Persistence.createEntityManagerFactory(applicationMode);
            return SINGLETON_FACTORY;
        }
        return SINGLETON_FACTORY;
    }

    /**
     * @return the application mode ( PRODUCTION or DEVELOPMENT )
     * @throws IOException
     */
    private static String getApplicationMode() {
        Properties properties = System.getProperties();
        try {
            properties.load(DatabaseManager.class.getResourceAsStream(APPLICATION_PROPERTIES_RESOURCE));
            return properties.getProperty("mode");
        } catch (IOException e) {
            throw new AssertionError("Cannot retrieve required resource", e);
        }
    }


    /**
     * Saves or updates entities in the database
     *
     * @param entities entities to be saved or updated
     */
    public void saveOrUpdate(Object... entities) {
        applyTransaction((entity) -> {
            em.merge(entity);
//            Session session = em.unwrap(Session.class);
//            session.saveOrUpdate(entity);
        }, entities);
    }

    /**
     * Saves entities in the database ( not updating )
     *
     * @param entities entities to be saved
     */
//    public void save(Object... entities) {
//        applyTransaction(em::persist, entities);
//    }

    /**
     * Removes entities from database
     *
     * @param entities
     */
    public void remove(Object... entities) {
        applyTransaction((entity) -> {
            if (!em.contains(entity)) {
                Object mergedEntity = em.merge(entity);
                em.remove(mergedEntity);
            } else {
                em.remove(entity);
            }
        }, entities);
    }

    /**
     * @return the entity manager of the database
     */
    public EntityManager getEntityManager() {
        return em;
    }

    private void applyTransaction(Consumer<Object> consumer, Object... entities) {
        em.getTransaction().begin();
        for (Object entity : entities) {
            consumer.accept(entity);
        }
        em.getTransaction().commit();
    }


    public <T> T findByLongId(Class<T> className, long id) {
        return em.find(className, id);
    }

    public <T> List<T> findAll(Class<T> className) {
        String name = className.getName();
        String query = "Select c from " + name + " c";
        TypedQuery<T> selectQuery = em.createQuery(query, className);
        return selectQuery.getResultList();
    }

    public <T> void clear(Class<T> className) {
        String name = className.getName();
        String deleteQuery = "DELETE from " + name;
        Query query = em.createQuery(deleteQuery);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
    }



    public void fillDatabaseWithLocations() throws IOException {
        try (InputStream locationsStream = DatabaseManager.class.getResourceAsStream(Resources.RESOURCE_XLS_LAPOSTE_HEXASMAL_CSV)) {
            List<Location> locations = Parser.parseCSVPostCode(locationsStream);
            Location[] locationsArray = locations.toArray(new Location[locations.size()]);
            saveOrUpdate(locationsArray);
        }
    }
}
