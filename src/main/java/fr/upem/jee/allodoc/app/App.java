package fr.upem.jee.allodoc.app;

import fr.upem.jee.allodoc.jpa.Patient;
import fr.upem.jee.allodoc.jpa.User;

import java.io.IOException;

/**
 * Created by raptao on 12/13/2016.
 */
public class App {

    public static void main(String[] args) throws IOException {
        User u = new User();
        u.setFirstName("thierry");
        u.setLastName("rabearijao");
        Patient p = new Patient();
        p.setFirstName("Pape");
        p.setLastName("NDIAYE");
        System.out.println(p.getFirstName());
    }
}
