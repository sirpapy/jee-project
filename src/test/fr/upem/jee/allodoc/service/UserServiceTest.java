package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.User;
import fr.upem.jee.allodoc.sample.SampleUsers;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserServiceTest {
//    @Test
//    public void controllerTest() throws Exception {
//        UserServiceImpl controller = new UserServiceImpl();
//        User u = new User();
//        u.setFirstName("thierry");
//        u.setLastName("rabearijao");
//        u.setEmail("thierryrabearijao@mail.com");
//        u.setPassword("password");
//        controller.saveOrUpdate(u);
//        Optional<User> authenticate = controller.authenticate("thierryrabearijao@mail.com", "password");
//        assertTrue( authenticate.isPresent());
//        controller.remove(u);
//    }


    @Test
    public void findByLogin(){
        Patient patient = SampleUsers.patient();
        PatientService patientService = new PatientService(patient);
        patientService.save();

        Optional<User> byLogin = patientService.findByLogin(patient.getAccount().getUserName());
        assertTrue( byLogin.isPresent());

    }
}