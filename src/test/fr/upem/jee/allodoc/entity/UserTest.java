package fr.upem.jee.allodoc.entity;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by raptao on 12/13/2016.
 */
public class UserTest {

    private final User u;

    public UserTest() throws IOException {
        u = new User();
    }

    @Test
    public void setAndGetId() throws Exception {
        u.setId(1L);
        assertEquals(Long.valueOf(1L), u.getId());
    }

    @Test
    public void setAndGetFirstName() throws Exception {
        u.setFirstName("thierry");
        assertEquals("thierry", u.getFirstName());
    }

    @Test
    public void setAndGetLastName() throws Exception {
        u.setLastName("thierry");
        assertEquals("thierry", u.getLastName());
    }

    @Test
    public void setAndGetEmail() throws Exception {
        u.setEmail("user@mail.com");
        assertEquals("user@mail.com", u.getEmail());
    }

    @Test
    public void setAndGetPhoneNumber() throws Exception {
        u.setPhoneNumber("01234");
        assertEquals("01234", u.getPhoneNumber());
    }

    @Test
    public void setAndGetAddress() throws Exception {
        Address address = new AddressBuilder().createAddress();
        address.setId(1L);
        address.setRegion(93 , "Seine Saint-Denis", "France");
        address.setStreetName("streetName");
        address.setStreetNumber("2 bis");
        u.setAddress(address);
        assertEquals(address, u.getAddress());


    }

    @Test
    public void setAndGetPassword() throws Exception {
        u.setPassword("password");
        assertEquals("password", u.getPassword());
    }


}