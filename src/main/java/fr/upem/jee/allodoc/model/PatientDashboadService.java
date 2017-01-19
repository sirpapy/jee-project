package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.controller.FieldOfActivityController;
import fr.upem.jee.allodoc.controller.PatientController;
import fr.upem.jee.allodoc.controller.PhysicianController;
import fr.upem.jee.allodoc.jpa.Availability;
import fr.upem.jee.allodoc.jpa.FieldOfActivity;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sirpapy on 19/01/2017.
 */



class PatientDashboadService {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");
    private PhysicianController physicianController = new PhysicianController();
    private PatientController patientController = new PatientController();



    private void getDummyData() throws ParseException {
        Physician physician = new Physician();
        FieldOfActivityController fieldOfActivityController = new FieldOfActivityController();
        FieldOfActivity fieldOfActivity = new FieldOfActivity("GENERALISTE");
        PhysicianController controller = new PhysicianController(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        fieldOfActivity = new FieldOfActivity("GENERALISTE");
        fieldOfActivityController.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        physician.setLastName("NDIAYE");
        physician.setFirstName("PAPE");
        fieldOfActivity = new FieldOfActivity("Gynéco");
        fieldOfActivityController.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();


        physician.setLastName("NDIAYE");
        physician.setFirstName("PAPEZ");
        fieldOfActivity = new FieldOfActivity("Neurologue");
        fieldOfActivityController.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

    }

    public List<Physician> getListOfPhysician(String name) throws ParseException {
        getDummyData();
        return physicianController.searchByName(name);
    }
}
