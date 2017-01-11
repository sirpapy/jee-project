package fr.upem.jee.allodoc.app;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.jpa.Parser;
import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.jpa.Physician;
import fr.upem.jee.allodoc.jpa.User;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by raptao on 12/13/2016.
 */
public class App {

    public static void main(String[] args) throws IOException {
//        DatabaseManager manager = new DatabaseManager("DEV-MODE");
//        User u = new User();
//        u.setFirstName("thierry");
//        u.setLastName("rabearijao");
//        u.setEmail("thierryrabearijao@mail.com");
//        manager.save(u);
//        TypedQuery<User> query = manager.getEntityManager().createQuery("Select u from User u where u.firstName='thierry' and u.lastName='rabearijao'", User.class);
//        User singleResult = query.getResultList().get(0);
//        System.out.println(singleResult.getFirstName());
    Parser.parseCSVDoctor(Paths.get("../resources/XLS/liste_des_medecins_accredites_extraction_du_07_01_2015_csv.csv"));

    }
}
