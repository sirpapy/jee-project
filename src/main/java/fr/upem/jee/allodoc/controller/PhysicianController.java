package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.persistence.TypedQuery;
import java.util.Collection;

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

    public Collection<Availability> getAvailabilities(Physician physician){
        Preconditions.checkNotNull(physician);
        TypedQuery<Availability> query = manager().getEntityManager().createNamedQuery("getPhysicianAvailabilies", Availability.class);
        query.setParameter("pId", physician.getId());
        return query.getResultList();
    }

}
