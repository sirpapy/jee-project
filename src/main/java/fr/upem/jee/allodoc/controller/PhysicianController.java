package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

/**
 * Created by raptao on 12/14/2016.
 */
public class PhysicianController extends Controller<Physician> {

    public PhysicianController() {
        super();
    }


    public Physician getFromId(Long id) {
        return manager().getEntityManager().find(Physician.class, id);
    }

    public List<Physician> search( String firstName, String lastName){
        Preconditions.checkNotNull(firstName , "firstName should not be null");
        Preconditions.checkNotNull(lastName, "lastName should not be null");
        TypedQuery<Physician> query = manager().getEntityManager().createNamedQuery("findPhysicianFirstnameLastName", Physician.class);
        query.setParameter("pLastName", lastName);
        query.setParameter("pFirstName", firstName);
        return query.getResultList();
    }
    public Collection<Availability> getAvailabilities(Physician physician){
        Preconditions.checkNotNull(physician);
        TypedQuery<Availability> query = manager().getEntityManager().createNamedQuery("getPhysicianAvailabilies", Availability.class);
        query.setParameter("pId", physician.getId());
        return query.getResultList();
    }


    public static void savePhysician(Physician physician) {
        Controller<Object> controller = Controller.getController(Controller.class);
        EntityManager entityManager = controller.manager().getEntityManager();
        entityManager.getTransaction().begin();
//        PhysicianAvailability physicianAvailability = physician.getPhysicianAvailability().get(0);
//        entityManager.persist(physicianAvailability);
        physician.getAvailabilities().forEach(entityManager::persist);
        entityManager.persist(physician);
        entityManager.getTransaction().commit();
    }
}
