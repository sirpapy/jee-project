package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.jpa.Physician;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by raptao on 12/14/2016.
 */
public class PhysicianController extends Controller<Physician> {

    public PhysicianController() {
        super();
    }

    public Physician getFromId( long id ){
        PhysicianController controller = Controller.getController(PhysicianController.class);
        TypedQuery<Physician> getPhysicianFromId = controller.manager().getEntityManager().createNamedQuery("getPhysicianFromId", Physician.class);
        getPhysicianFromId.setParameter("pId", id);
        List<Physician> resultList = getPhysicianFromId.getResultList();
        if(resultList.isEmpty()){
            throw new IllegalArgumentException("There is no physician with this id");
        }
        return resultList.get(0);
    }


}
