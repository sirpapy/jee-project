package fr.upem.jee.allodoc.jpa;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Pape on 17/13/2016.
 */
public class AppointmentTest {
    private Appointment a1;
    private Appointment a2;
    private final Patient u;
    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");
    public AppointmentTest() throws IOException, ParseException {
        u = new Patient();
        a1 = new Appointment(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30"));
        a2 = new Appointment(f.parse("07-06-2013 12:30"), f.parse("07-06-2013 12:45"));
    }



    @Test
    public void isFreeAppointmentTest() throws Exception {
        assertTrue(a1.isFree());
    }

    @Test
    public void setAppointmentTest() throws Exception {
        u.setId(1L);
        a1.setAppointment(u.getId());
        assertFalse(a1.isFree());
    }

    @Test
    public void removeAppointmentTest() throws Exception {
        a1 = new Appointment(f.parse("07-06-2013 12:05"), f.parse("07-06-2013 12:30"));
        u.setId(1L);
        a1.setAppointment(u.getId());
        a1.removeAppointment();
        assertEquals(true, a1.isFree());
    }

}