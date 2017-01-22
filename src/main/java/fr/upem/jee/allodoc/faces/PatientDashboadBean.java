package fr.upem.jee.allodoc.faces;


import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
//import fr.upem.jee.allodoc.utilities.Parser;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
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
    PatientDashboardService patientDashboadService = new PatientDashboardService();
    public List<Physician> getDoctorResult() throws ParseException {
        return patientDashboadService.getListOfPhysician(searchBean.getName());
    }

    public static HashMap<Integer,String> getPostalCodeList() throws IOException {
        HashMap<Integer,String> toReturn = new HashMap<>();
//        List<Location> locations = Parser.parseCSVPostCode(Paths.get("D:\\workspace\\jee-project\\src\\main\\resources\\XLS\\laposte_hexasmal.csv"));
//        for (Location location : locations){
//            toReturn.put(location.getPostalCode(), location.getCity());
//        }
        toReturn.put(75020,"Paris 20");
        toReturn.put(75018,"Paris 18");
        toReturn.put(75015,"Paris 15");
        toReturn.put(75010,"Paris 10");

        return toReturn;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }
}
