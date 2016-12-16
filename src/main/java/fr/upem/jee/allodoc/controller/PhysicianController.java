package fr.upem.jee.allodoc.controller;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * Created by raptao on 12/14/2016.
 */
public class PhysicianController extends Controller<Physician> {

    private Physician physician;

    public PhysicianController(Physician physician) {
        Preconditions.checkNotNull("physician should not be null");

        this.physician = physician;
    }

    public PhysicianController() {
        super();
    }

    public static PhysicianController getFromId( long id ){
        PhysicianController controller = Controller.getController(PhysicianController.class);
        TypedQuery<Physician> getPhysicianFromId = controller.manager().getEntityManager().createNamedQuery("getPhysicianFromId", Physician.class);
        getPhysicianFromId.setParameter("pId", id);
        List<Physician> resultList = getPhysicianFromId.getResultList();
        if(resultList.isEmpty()){
            throw new IllegalArgumentException("There is no physician with this id");
        }
        controller.setPhysician(resultList.get(0));
        return controller;
    }

    public void setPhysician(Physician physician) {
        Preconditions.checkNotNull("physician should not be null");
        this.physician = physician;
    }


    public void addAvailability(Availability newAvailability){
        Preconditions.checkNotNull(newAvailability, "newAvailability should not be null");
    }

    public Set<Availability> getAvailabilities(){
//        manager().getEntityManager().createNamedQuery("getPhysicianAvailabilities", );
        return null;
    }
}
