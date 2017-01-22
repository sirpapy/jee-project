package fr.upem.jee.allodoc.faces;

import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertFalse;

/**
 * Created by raptao on 1/22/2017.
 */
public class PatientDashboardBeanTest {
    @Test
    public void getPostalCodeList() throws Exception {
        Map<Integer, String> postalCodeList = PatientDashboardBean.getPostalCodeList();
        assertFalse(postalCodeList.isEmpty());
    }

}