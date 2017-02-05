package fr.upem.jee.allodoc.sample;

import fr.upem.jee.allodoc.entity.*;

import java.sql.Date;

/**
 * Created by raptao on 2/4/2017.
 */
public class SampleUsers {

    public static Physician physician(){
        Address montfermeil = new Address.Builder().setLocation(new Location(93, "MONTFERMEIL")).build();
        return new Physician.Builder()
                .setAccount(new Account("email", "password"))
                .setFirstName("firstName")
                .setLastName("lastName")
                .setAddress(montfermeil)
                .setFieldOfActivity(new FieldOfActivity("science field"))
                .setBirthDate(Date.valueOf("1992-09-28"))
                .setPracticeArea(new Location(93, "sd"))
                .setStatus("public").build();
    }

}
