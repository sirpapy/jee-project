package fr.upem.jee.allodoc;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by raptao on 12/13/2016.
 */
public class DatabaseManager {

    public static final String APPLICATION_PROPERTIES_RESOURCE = "/application.properties";
    private static EntityManagerFactory SINGLETON_FACTORY;
    private EntityManager em;


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
        }
        return SINGLETON_FACTORY;
//        return Persistence.createEntityManagerFactory(applicationMode);
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

    private static EntityManager newEntityManager() {
        return getFactory(getApplicationMode()).createEntityManager();
    }

    /**
     * Saves or updates entities in the database
     *
     * @param entities entities to be saved or updated
     */
    public void saveOrUpdate(Object... entities) {
        this.em = newEntityManager();
        this.em.getTransaction().begin();
        for (Object entity : entities) {

            em.merge(entity);
        }
//            Session session = em.unwrap(Session.class);
//            session.saveOrUpdate(entity);
        this.em.getTransaction().commit();
        this.em.close();
//        this.em.getEntityManagerFactory().close();
    }

    /**
     * Saves entities in the database ( not updating )
     *
     * @param entities entities to be saved
     */
    public void save(Object... entities) {
        this.em = newEntityManager();
        this.em.getTransaction().begin();
        for (Object entity : entities) {
            this.em.persist(entity);
        }
        this.em.getTransaction().commit();
        this.em.close();
//        this.em.getEntityManagerFactory().close();
    }

    /**
     * Removes entities from database
     *
     * @param entities
     */
    public void remove(Object... entities) {
        this.em = newEntityManager();
        em.getTransaction().begin();
        for (Object entity : entities) {
            if (!em.contains(entity)) {
                Object mergedEntity = em.merge(entity);
                em.remove(mergedEntity);
            } else {
                em.remove(entity);
            }
        }
        this.em.getTransaction().commit();
        this.em.close();
//        this.em.getEntityManagerFactory().close();
    }

    /**
     * @return the entity manager of the database
     */
    public EntityManager getEntityManager() {
        return newEntityManager();
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
        this.em = newEntityManager();
        Query query = em.createQuery(deleteQuery);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        this.em.close();
//        this.em.getEntityManagerFactory().close();
    }

    public <T> List<T> executeQuery(String qlString, Class<T> target) {
        this.em = newEntityManager();
        TypedQuery<T> typedQuery = em.createQuery(qlString, target);
        return typedQuery.getResultList();
    }

}
