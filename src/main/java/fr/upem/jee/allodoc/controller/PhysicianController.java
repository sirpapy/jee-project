package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by raptao on 12/14/2016.
 */
public class PhysicianController extends Controller<Physician> {

    private final Physician physician;

    /**
     * Initializes a newly created {@link PhysicianController} object.
     * This controller will take control of a new {@link Physician}
     */

    public PhysicianController(Physician physician){
        super();
        this.physician = Objects.requireNonNull(physician);
    }

    public static Physician getFromId(Long id) {
        return DatabaseManager.getDatabaseManager().getEntityManager().find(Physician.class, id);
    }

    public List<Physician> search( String firstName, String lastName){
        Preconditions.checkNotNull(firstName , "firstName should not be null");
        Preconditions.checkNotNull(lastName, "lastName should not be null");
        TypedQuery<Physician> query = manager().getEntityManager().createNamedQuery("findPhysicianFirstnameLastName", Physician.class);
        query.setParameter("pLastName", lastName);
        query.setParameter("pFirstName", firstName);
        return query.getResultList();
    }

    public Collection<Availability> getAvailabilities(){
        Query query = manager().getEntityManager().createNativeQuery("select availability_id from physician_availability where physician_id = "+physician.getId());
        List<BigInteger> resultList = query.getResultList();
        String collect = resultList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        TypedQuery<Availability> availabilities = manager().getEntityManager().createQuery("select a from Availability a where a.id in ("+collect+")", Availability.class);
        return availabilities.getResultList();
    }

    /**
     * Saves the current controlled physician
     */
    public void save(){
        super.save(physician);
    }

}
