package fr.upem.jee.allodoc.service;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.Availability;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.utilities.Parser;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by raptao on 12/14/2016.
 */
public class PhysicianService extends UserServiceImpl<Physician> {

    private Physician physician;

    /**
     * Initializes a newly created {@link PhysicianService} object.
     * This controller will take control of a new {@link Physician}
     */
    public PhysicianService(Physician physician) {
        super();
        this.physician = Objects.requireNonNull(physician);
    }

    public PhysicianService() {
        super();
    }

    /**
     * @param id the id of the physician
     * @return the {@link Physician} object with the id
     */
    public static Physician getById(Long id) {
        return DatabaseManager.getDatabaseManager().getEntityManager().find(Physician.class, id);
    }

    /**
     * This methods takes control of a new {@link Physician} object.
     *
     * @param physician the {@link Physician} to be taken control of
     */
    @Override
    public void takeControl(Physician physician) {
        Objects.requireNonNull(physician);
        this.physician = physician;
    }

    /**
     * Searches and returns the list of physician with the firstName and the lastName given in argument
     *
     * @param firstName the firstName of the physician
     * @param lastName  the lastName of the physician
     * @return the list of physician, empty list if there is no physician with these firstName and lastName
     */
    public List<Physician> search(String firstName, String lastName) {
        Preconditions.checkNotNull(firstName, "firstName should not be null");
        Preconditions.checkNotNull(lastName, "lastName should not be null");
        TypedQuery<Physician> query = manager().getEntityManager().createNamedQuery("findPhysicianFirstnameLastName", Physician.class);
        query.setParameter("pLastName", lastName);
        query.setParameter("pFirstName", firstName);
        return query.getResultList();
    }


    /**
     * Searches and returns the list of physician with the firstName and the lastName given in argument
     * @param name the name of the physician (First or last name)
    * @return the list of physician, empty list if there is no physician with that name
     */
    public List<Physician> searchByName(String name) {
        Preconditions.checkNotNull(name, "firstName should not be null");
        TypedQuery<Physician> query = manager().getEntityManager().createNamedQuery("findPhysicianName", Physician.class);
        query.setParameter("pName", "%"+name+"%");
        return query.getResultList();
    }



    /**
     * Searches and returns the list of physician with the firstName and the lastName given in argument
     * @param fieldOfActivity the name of the physician (First or last name)
    * @return the list of physician, empty list if there is no physician with that name
     */
    public List<Physician> searchByFieldOfActivity(String fieldOfActivity) {
        Preconditions.checkNotNull(fieldOfActivity, "field of activity should not be null");
        TypedQuery<Physician> query = manager().getEntityManager().createNamedQuery("findPhysicianFieldOfActivity", Physician.class);
        query.setParameter("pField", fieldOfActivity);
        return query.getResultList();
    }


    /**
     * Searches and returns the list of physician with the firstName and the lastName given in argument
     * @param name the name of the physician (First or last name)
    * @return the list of physician, empty list if there is no physician with that name
     */
    public List<Physician> searchByNameFieldOfActivityLocation(FieldOfActivity fieldOfActivity, String name, Location location) {
        Preconditions.checkNotNull(fieldOfActivity, "field of activity should not be null");
        Preconditions.checkNotNull(name, "The name should not be null");
        Preconditions.checkNotNull(location, "The location should not be null");
        TypedQuery<Physician> query = manager().getEntityManager().createNamedQuery("findPhysicianByNameFieldOfActivityLocation", Physician.class);
        query.setParameter("pField", '%'+fieldOfActivity.getName()+'%');
        query.setParameter("pCity", '%'+String.valueOf(location.getPostalCode())+'%');
        query.setParameter("pName", '%'+name+'%');
        return query.getResultList();
    }

    /**
     * @return all availabilities of the currently controlled {@link Physician}
     */
    // TODO remove this uneeded method
    public Collection<Availability> getAvailabilities() {
        // retrieving all availability_id for the physician
        Query query = manager().getEntityManager().createNativeQuery("select availability_id from physician_availability where physician_id = " + physician.getId());
        List<BigInteger> resultList = query.getResultList();
        String collect = resultList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        // retrieving all details about availability
        TypedQuery<Availability> availabilities = manager().getEntityManager().createQuery("select a from Availability a where a.id in (" + collect + ")", Availability.class);
        return availabilities.getResultList();
    }

    /**
     * @param availabilityId the id of the scheduled time
     * @return true if the controlled physician is available on the time specified by the availability id
     */
    public boolean isAvailableAt(long availabilityId) {
        String queryString = String.format("select appointment_id FROM  physician_availability where availability_id = %d and physician_id = %d ", availabilityId, physician.getId());
        Query appointmentIdQuery = manager().getEntityManager().createNativeQuery(queryString);
        List resultList = appointmentIdQuery.getResultList();
        return resultList.isEmpty();
    }

    /**
     * Saves the current controlled physician
     */
    @Override
    public void save() {
        FieldOfActivity fieldOfActivity = physician.getFieldOfActivity();
        Optional<FieldOfActivity> byName = FieldOfActivityService.getByName(fieldOfActivity.getName());
        if( byName.isPresent()){
            System.out.println("ALREADY IN DB:" + byName.get().getName());
            physician.setFieldOfActivity(byName.get());
        }
        save(physician);
    }

    public static void fillDatabaseWithPhysicians() throws IOException {
        PhysicianService physicianService = new PhysicianService();
        try (InputStream physiciansStream = DatabaseManager.class.getResourceAsStream(Resources.RESOURCE_XLS_PHYSICIANS_CSV)) {
            List<Physician> physicians = Parser.parseCSVPhysicians(physiciansStream);
            physicians.forEach(p->{
                physicianService.takeControl(p);
                physicianService.save();
            });
        }
    }

    public static List<Physician> getAll(){
        return DatabaseManager.getDatabaseManager().findAll(Physician.class);
    }


}
