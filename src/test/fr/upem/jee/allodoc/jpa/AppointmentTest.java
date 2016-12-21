package fr.upem.jee.allodoc.jpa;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Pape on 17/13/2016.
 */
public class AppointmentTest {
    private final Appointment a1;
    private final Appointment a2;
    private final Patient u;
    SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MMM-yyyy:HM-MM");

    public AppointmentTest() throws IOException {
        a1 = new Appointment(dateFormater.parse("7-Jun-2013:12:05"), dateFormater.parse("7-Jun-2013:12:30"))
        a2 = new Appointment(dateFormater.parse("7-Jun-2013:12:30"), dateFormater.parse("7-Jun-2013:12:45"))
    }



    @Test
    public void isFreeAppointmentTest() throws Exception {
        assertEquals(false, a2.isFree());
    }

    @Test
    public void setAppointmentTest() throws Exception {
        u.setId(1L);
        a1.setAppointment(u.getId());
        assertEquals(true, a1.isFree());
    }

    @Test
    public void removeAppointmentTest() throws Exception {
        a1 = new Appointment(dateFormater.parse("7-Jun-2013:12:05"), dateFormater.parse("7-Jun-2013:12:30"));
        u.setId(1L);
        a1.setAppointment(u.getId());
        a1.removeAppointment();
        assertEquals(true, a1.isFree());
    }

}}