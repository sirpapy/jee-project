package fr.upem.jee.allodoc.utilities;

import fr.upem.jee.allodoc.entity.*;
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

    public static final String DOMAIN_ALLODOC_FR = "allodoc.fr";
    private static final String DEFAULT_PHYSICIAN_PASSWORD = "allodocPhysician";

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
            if( fieldOfActivity.startsWith("\"")){
                fieldOfActivity = fieldOfActivity.substring(1);
            }
            String practiceAreaDepartment = columns[5];
            String practiceAreaRegion = columns[6];
            String status = columns[columns.length - 1];
            Physician physician = new Physician.Builder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setAccount(new Account(buildDefaultEmail(firstName, lastName), DEFAULT_PHYSICIAN_PASSWORD))
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
                .stream().collect(Collectors.toList());
    }

    public static List<Physician> parseCSVPhysicians(InputStream csvInputStream, long limit) throws IOException {
        List<Physician> physicians = parseCSVPhysicians(csvInputStream);
        return physicians.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    private static String buildDefaultEmail(String firstName, String lastName) {
        return cleanName(firstName + "." + lastName) + "@" + DOMAIN_ALLODOC_FR;
    }

    private static String cleanName(String name) {
        return name.toLowerCase().replace(" ", "");
    }

    /**
     * Returns a distinct list of {@link Location} objects fetched from the csvInputStream.
     *
     * @param csvInputStream input stream where data are fetched from
     * @return the list of location
     * @throws IOException in case of I/O error
     */
    public static List<Location> parseCSVPostCode(InputStream csvInputStream, long dataLimit) throws IOException {
        List<Location> locations = parseCSVPostCode(csvInputStream);
        return locations.stream()
                .limit(dataLimit)
                .collect(Collectors.toList());
    }

    public static List<Location> parseCSVPostCode(InputStream csvInputStream) throws IOException {
        List<String> dataOnPostCodeCSV = IOUtils.readLines(csvInputStream, StandardCharsets.UTF_8);
        dataOnPostCodeCSV.remove(0);
        return dataOnPostCodeCSV.stream()
                .skip(1)
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
