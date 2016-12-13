package fr.upem.jee.allodoc;

import fr.upem.jee.allodoc.jpa.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import javax.persistence.TypedQuery;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by raptao on 12/13/2016.
 */
public class DatabaseManagerTest {


    @Test
    public void saveAndRemove() throws Exception {
        // initialization
        DatabaseManager manager = new DatabaseManager("DEV-MODE");
        User u = new User();
        u.setFirstName("thierry");
        u.setLastName("rabearijao");
        u.setEmail("thierryrabearijao@mail.com");
        manager.save(u);
        TypedQuery<User> query = manager.getEntityManager().createQuery("Select u from User u where u.firstName='thierry' and u.lastName='rabearijao'", User.class);
        User singleResult = query.getResultList().get(0);

        // test save
        assertEquals( u.getFirstName(), singleResult.getFirstName());
        assertEquals( u.getLastName(), singleResult.getLastName());
        assertEquals( u.getEmail(), singleResult.getEmail());
        manager.remove(singleResult);
        TypedQuery<User> removed = manager.getEntityManager().createQuery("Select u from User u where u.firstName='thierry' and u.lastName='rabearijao'", User.class);

        // test removed
        assertEquals(0, removed.getResultList().size());
    }

    @Test
    public void getEntityManager() throws Exception {

    }

}