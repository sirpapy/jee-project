package fr.upem.jee.allodoc.sample;

import fr.upem.jee.allodoc.entity.*;

import java.sql.Date;

/**
 * Created by raptao on 2/4/2017.
 */
public class SampleUsers {

    public static Physician physician(){
        Address montfermeil = new Address.Builder().setLocation(new Location(93370, "MONTFERMEIL")).build();
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

    public static Physician thierry(){
        Address montfermeil = new Address.Builder().setLocation(new Location(91000, "MONTFERMEIL")).build();
        return new Physician.Builder()
                .setAccount(new Account("email", "password"))
                .setFirstName("thierry")
                .setLastName("raptao")
                .setAddress(montfermeil)
                .setFieldOfActivity(new FieldOfActivity("it field"))
                .setBirthDate(Date.valueOf("1992-09-28"))
                .setPracticeArea(new Location(91000, "vxcvxcv"))
                .setStatus("public").build();
    }


    public static Patient patient(){
        return new Patient.Builder()
                .setFirstName("raptao")
                .setLastName("thierry")
                .setAccount(new Account("thierry@raptao", "patientPassword"))
                .build();
    }
}
