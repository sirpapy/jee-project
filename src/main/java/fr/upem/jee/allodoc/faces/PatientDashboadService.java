package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.service.FieldOfActivityService;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.entity.Availability;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Physician;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sirpapy on 19/01/2017.
 */



class PatientDashboadService {
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");
    private PhysicianService physicianService = new PhysicianService();
    private PatientService patientService = new PatientService();



    private void getDummyPhysicianData() throws ParseException {
        Physician physician = new Physician();
        FieldOfActivityService fieldOfActivityService = new FieldOfActivityService();
        FieldOfActivity fieldOfActivity = new FieldOfActivity("GENERALISTE");
        PhysicianService controller = new PhysicianService(physician);
        physician.setLastName("raptao");
        physician.setFirstName("thierry");
        fieldOfActivity = new FieldOfActivity("GENERALISTE");
        fieldOfActivityService.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

        physician.setLastName("NDIAYE");
        physician.setFirstName("PAPE");
        fieldOfActivity = new FieldOfActivity("Gynéco");
        fieldOfActivityService.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();


        physician.setLastName("NDIAYE");
        physician.setFirstName("PAPEZ");
        fieldOfActivity = new FieldOfActivity("Neurologue");
        fieldOfActivityService.save(fieldOfActivity);
        physician.setFieldOfActivity(fieldOfActivity);
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30")));
        physician.setAvailability(new Availability(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45")));
        controller.save();

    }

    public List<Physician> getListOfPhysician(String name) throws ParseException {
        getDummyPhysicianData();
        return physicianService.searchByName(name);
    }
}
