package fr.upem.jee.allodoc.utilities;

import fr.upem.jee.allodoc.entity.Address;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by Sirpapy on 30/11/2016.
 */
public class Parser {
    public static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    public static final String CONSTANT_FRANCE = "France";

    public static List<Physician> parseCSVDoctor(Path path) throws IOException {
        List<String> dataOnDoctorCSV = Files.readAllLines(Objects.requireNonNull(path), CHARSET_UTF8);

        List<Physician> toReturn = new ArrayList<>();
        for (String line : dataOnDoctorCSV) {
            String[] columns = line.split(";");
            String lastName = columns[0];
            String firstName = columns[1];
            String fieldOfActivity = columns[2];
            String dateAccreditation = columns[3];
            String nomDepartement = columns[5];
            String regionExercice = columns[6];
            String status = columns[8];
            Physician ph = new Physician.Builder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setDateAccreditation(dateAccreditation)
                    .setRegionExercice(regionExercice)
                    .setStatus(status)
                    .setNomDepartement(nomDepartement).build();

            // TODO finish it
//            FieldOfActivity foc = FieldOfActivityController.getSelectedFieldOfActivity(fieldOfActivity);
//            if (foc != null) {
//                ph.setSelectedFieldOfActivity(foc);
//            } else {
//                save(new FieldOfActivity(fieldOfActivity));
//            }
            Address address = new Address();
            address.setStreetName("");
            address.setStreetNumber("");
            ph.setAddress(address);
            toReturn.add(ph);
        }
        return toReturn;
    }

    public static List<Location> parseCSVPostCode(Path path) throws IOException {
        List<String> dataOnPostCodeCSV = Files.readAllLines(Objects.requireNonNull(path), CHARSET_UTF8);

        List<Location> toReturn = new ArrayList<>();
        for (String line : dataOnPostCodeCSV) {
            String[] columns = line.split(";");
            String name = columns[1];
            String postCode = columns[2];
            toReturn.add(new Location(Integer.valueOf(postCode), name, CONSTANT_FRANCE));
        }
        return toReturn;
    }

}
