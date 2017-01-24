package fr.upem.jee.allodoc.utilities;

import fr.upem.jee.allodoc.entity.Address;
import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Sirpapy on 30/11/2016.
 */
public class Parser {

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
        dataOnDoctorCSV.remove(0);
        List<Physician> physicians = new ArrayList<>();
        for (String line : dataOnDoctorCSV) {
            String[] columns = line.split(";");
            String lastName = columns[0];
            String firstName = columns[1];
            String fieldOfActivity = columns[2];
            String practiceAreaDepartment = columns[5];
            String practiceAreaRegion = columns[6];
            String status = columns[columns.length -1];
            Physician physician = new Physician.Builder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setFieldOfActivity(new FieldOfActivity(fieldOfActivity))
                    .setPracticeArea(new Location.Builder().setCity(practiceAreaRegion).build())
                    .setStatus(status).build();
            Address address = new Address.Builder()
                    .setLocation(new Location.Builder().setCity(practiceAreaDepartment).build())
                    .build();
            physician.setAddress(address);
            physicians.add(physician);
        }
        return physicians
                // TODO : delete the line below for prod
                .stream().limit(100).collect(Collectors.toList());
    }

    /**
     * Returns a distinct list of {@link Location} objects fetched from the csvInputStream.
     *
     * @param csvInputStream input stream where data are fetched from
     * @return the list of location
     * @throws IOException in case of I/O error
     */
    public static List<Location> parseCSVPostCode(InputStream csvInputStream) throws IOException {
        List<String> dataOnPostCodeCSV = IOUtils.readLines(csvInputStream, StandardCharsets.UTF_8);
        dataOnPostCodeCSV.remove(0);
        return dataOnPostCodeCSV.stream()
                .skip(1)
                .limit(100) // TODO : delete line this for prod
                .map(line -> line.split(";"))
                .map(tokens -> {
                    String name = tokens[1];
                    String postCode = tokens[2];
                    return new Location.Builder()
                            .setPostalCode(Integer.parseInt(postCode))
                            .setCity(name).build();
                })
                .distinct().collect(Collectors.toList());
    }

}
