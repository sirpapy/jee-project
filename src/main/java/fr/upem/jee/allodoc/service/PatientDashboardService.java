package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.Physician;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sirpapy on 19/01/2017.
 */


public class PatientDashboardService {


    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");
    private PhysicianService physicianService = new PhysicianService();
    private PatientService patientService = new PatientService();




    public List<Physician> getListOfPhysician(String name) throws ParseException {
        return physicianService.searchByName(name);
    }
}
