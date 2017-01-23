package fr.upem.jee.allodoc.faces;


import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.utilities.Parser;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sirpapy on 19/01/2017.
 */


@ManagedBean(name = "patientDashboadBean", eager = true)
@RequestScoped
public class PatientDashboardBean {



    PatientDashboardService patientDashboardService = new PatientDashboardService();
    private String doctorName;
    private long postalCode;
    private String fieldOfActivity;


    @ManagedProperty("#{searchBean}")
    private SearchBean searchBean;

    public PatientDashboardBean() {
    }

    public static Map<Integer, String> getPostalCodeList() throws IOException {
        HashMap<Integer, String> toReturn = new HashMap<>();
        try (InputStream resourceAsStream = PatientDashboardBean.class.getResourceAsStream(Resources.RESOURCE_XLS_LAPOSTE_HEXASMAL_CSV)) {
            List<Location> locations = Parser.parseCSVPostCode(resourceAsStream);
            for (Location location : locations) {
                toReturn.put(location.getPostalCode(), location.getCity());
            }
            return toReturn;
        }
    }

    public List<Physician> getDoctorResult() throws ParseException {
        return patientDashboardService.getListOfPhysician(searchBean.getName());
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }
}
