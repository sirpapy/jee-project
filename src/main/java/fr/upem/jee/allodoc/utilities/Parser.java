package fr.upem.jee.allodoc.utilities;

import fr.upem.jee.allodoc.entity.Address;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.service.LocationService;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sirpapy on 30/11/2016.
 */
public class Parser {
    public static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    public static final String CONSTANT_FRANCE = "France";

    /**
     * Returns a list of physicians with any data associated with those physician.
     * This method parses an {@link InputStream} of a CSV file.
     *
     * @param csvInputStream the input stream of the csv file containing all data about physicians
     * @return a list of {@link Physician} object
     * @throws IOException in case of I/O errors
     */
    public static List<Physician> parseCSVPhysicians(InputStream csvInputStream) throws IOException {
        List<String> dataOnDoctorCSV = IOUtils.readLines(csvInputStream, StandardCharsets.UTF_8);
        List<Physician> physicians = new ArrayList<>();
        for (String line : dataOnDoctorCSV) {
            String[] columns = line.split(";");
            String lastName = columns[0];
            String firstName = columns[1];
            String fieldOfActivity = columns[2];
            String practiceAreaDepartment = columns[5];
            String practiceAreaRegion = columns[6];
            String status = columns[8];
            Physician physician = new Physician.Builder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setFieldOfActivity(new FieldOfActivity(fieldOfActivity))
                    .setPracticeArea(LocationService.getByNamedArea(practiceAreaRegion))
                    .setStatus(status).build();
            Address address = new Address.Builder()
                    .setLocation(LocationService.getByNamedArea(practiceAreaDepartment))
                    .createAddress();
            physician.setAddress(address);
            physicians.add(physician);
        }
        return physicians;
    }

    public static List<Location> parseCSVPostCode(InputStream csvInputStream) throws IOException {
        List<String> dataOnPostCodeCSV = IOUtils.readLines(csvInputStream, StandardCharsets.UTF_8);
        dataOnPostCodeCSV.remove(0);
        List<Location> toReturn = new ArrayList<>();
        int cpt = 0;
        for (String line : dataOnPostCodeCSV) {
            if (cpt == 4) {
                break;
            }
            String[] columns = line.split(";");
            String name = columns[1];
            String postCode = columns[2];
            toReturn.add(new Location.Builder().setPostalCode(Integer.valueOf(postCode)).setCity(name).setCountry(CONSTANT_FRANCE).build());
            cpt++;
        }
        return toReturn;
    }

}
