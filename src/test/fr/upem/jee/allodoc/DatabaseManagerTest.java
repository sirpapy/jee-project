package fr.upem.jee.allodoc;

import fr.upem.jee.allodoc.entity.Account;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.service.FieldOfActivityService;
import org.junit.Test;

import javax.persistence.TypedQuery;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

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
        u.setAccount(new Account("thierryrabearijao@mail.com", "bla"));
        manager.saveOrUpdate(u);
        List<User> resultList = manager.executeQuery("Select u from User u where u.firstName='thierry' and u.lastName='rabearijao'", User.class);
        assertEquals(1, resultList.size());
        User singleResult = resultList.get(0);

        // test saveOrUpdate
        assertEquals( u.getFirstName(), singleResult.getFirstName());
        assertEquals( u.getLastName(), singleResult.getLastName());
        manager.remove(singleResult);
        TypedQuery<User> removed = manager.getEntityManager().createQuery("Select u from User u where u.firstName='thierry' and u.lastName='rabearijao'", User.class);

        // test removed
        assertEquals(0, removed.getResultList().size());
    }


    @Test
    public void findAllAndClear(){
        DatabaseManager manager = new DatabaseManager("DEV-MODE");
        FieldOfActivity a = new FieldOfActivity("a");
        FieldOfActivity b = new FieldOfActivity("b");
        FieldOfActivity c = new FieldOfActivity("c");
        manager.saveOrUpdate(a, b, c);
        assertEquals(3, FieldOfActivityService.getAll().size());
        manager.clear(FieldOfActivity.class);
        assertTrue(FieldOfActivityService.getAll().isEmpty());
    }





}