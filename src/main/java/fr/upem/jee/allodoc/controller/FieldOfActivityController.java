package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.FieldOfActivity;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by raptao on 12/14/2016.
 */
public class FieldOfActivityController {

    public static Optional<FieldOfActivity> getByName(String name){
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        TypedQuery<FieldOfActivity> findFAByName = databaseManager.getEntityManager().createNamedQuery("findFAByName", FieldOfActivity.class);
        findFAByName.setParameter("fa_name", name);
        List<FieldOfActivity> resultList = findFAByName.getResultList();
        return resultList.isEmpty()? Optional.empty() : Optional.of(resultList.get(0));
    }

    public static List<FieldOfActivity> getAll(){
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        return databaseManager.findAll(FieldOfActivity.class);
    }

    public static void distinctSave(FieldOfActivity fieldOfActivity){
        Objects.requireNonNull(fieldOfActivity);
        Optional<FieldOfActivity> byName = getByName(fieldOfActivity.getName());
        if( !byName.isPresent()){
            DatabaseManager.getDatabaseManager().save(fieldOfActivity);
        }
    }

}
