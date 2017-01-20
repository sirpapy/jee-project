package fr.upem.jee.allodoc.model;

import fr.upem.jee.allodoc.jpa.Location;
import fr.upem.jee.allodoc.jpa.Parser;
import fr.upem.jee.allodoc.jpa.Physician;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sirpapy on 19/01/2017.
 */


@ManagedBean(name = "patientDashboadBean", eager = true)
@RequestScoped
public class PatientDashboadBean {


    public PatientDashboadBean(){}
    private String doctorName;
    private long postalCode;
    private String fieldOfActivity;



    @ManagedProperty("#{searchBean}")
    private SearchBean searchBean;
    PatientDashboadService patientDashboadService = new PatientDashboadService();
    public List<Physician> getDoctorResult() throws ParseException {
        return patientDashboadService.getListOfPhysician(searchBean.getName());
    }

    public static List<Location> getPostalCodeList() throws IOException {
        List<Location> toReturn = new ArrayList<>();
        List<Location> locations = Parser.parseCSVPostCode(Paths.get("D:\\workspace\\jee-project\\src\\main\\resources\\XLS\\laposte_hexasmal.csv"));
        for (Location location : locations){
            toReturn.add(location.getPostalCode(), location.getCity());
        }
        return toReturn;
    }
    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }
}
