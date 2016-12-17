package fr.upem.jee.allodoc.jpa;

import fr.upem.jee.allodoc.controller.FieldOfActivityController;

import java.awt.geom.IllegalPathStateException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sirpapy on 30/11/2016.
 */
public class Parser {
    static Charset charset = Charset.forName("ISO-8859-1");
    private static List<Physician> parseCSVDoctor(String path) {
        List<String> dataOnDoctorCSV = null;
        List<Physician> listOfDoctor = new ArrayList<>();
        try {
            dataOnDoctorCSV = Files.readAllLines(Paths.get(path),charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dataOnDoctorCSV == null)
            throw new IllegalPathStateException("The file is not available");

        List<Physician> toReturn = new ArrayList<>();
        for (String line:dataOnDoctorCSV){
            String[] columns = line.split(";");
            String lastName = line.split(";")[0];
            String firstName = line.split(";")[1];
            String fieldOfActivity = line.split(";")[2];
            String dateAccreditation = line.split(";")[3];
            String nomOAAMedecin = line.split(";")[4];
            String nomDepartement = line.split(";")[5];
            String regionExercice   = line.split(";")[6];
            String finess   = line.split(";")[7];
            String status   = line.split(";")[8];
            //String lastName, String firstName, String mail, String phone, String address, String password
            Physician ph = new Physician(lastName, firstName);
            FieldOfActivity foc = FieldOfActivityController.getFieldOfActivity(fieldOfActivity);
            if(foc!=null) {
                ph.setFielOfActivity(foc);
            }else{
                FieldOfActivityController.save(new FieldOfActivity(fieldOfActivity));
            }
            Address address = new Address();
            address.setStreetName("");
            address.setStreetNumber("");
            address.setLocation(new );
            ph.setAddress();
            toReturn.add(ph);
        }
        return toReturn;
    }

    private static List<Location> parseCSVPostCode(String path) {
        List<String> dataOnPostCodeCSV = null;
        List<Location> listOfDoctor = new ArrayList<>();
        try {
            dataOnPostCodeCSV = Files.readAllLines(Paths.get(path),charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dataOnPostCodeCSV == null)
            throw new IllegalPathStateException("The file is not available");

        List<Location> toReturn = new ArrayList<>();
        for (String line:dataOnPostCodeCSV){
            String insee = line.split(";")[0];
            String name = line.split(";")[1];
            String postCode = line.split(";")[2];
            toReturn.add(new Location(Integer.valueOf(postCode), name, "France"));
        }
        return toReturn;
    }

}
