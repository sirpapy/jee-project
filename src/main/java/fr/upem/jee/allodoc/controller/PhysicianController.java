package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println(physician.getId());
        Query query = manager().getEntityManager().createNativeQuery("select availability_id from physician_availability where physician_id = "+physician.getId());
        List<BigInteger> resultList = query.getResultList();
        String collect = resultList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        TypedQuery<Availability> availabilities = manager().getEntityManager().createQuery("select a from Availability a where a.id in ("+collect+")", Availability.class);
        return availabilities.getResultList();
    }


}
