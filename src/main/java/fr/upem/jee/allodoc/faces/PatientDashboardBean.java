package fr.upem.jee.allodoc.faces;


import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Parser;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sirpapy on 19/01/2017.
 */

@SessionScoped
@ManagedBean(name = "patientDashboadBean", eager = true)
public class PatientDashboardBean {


    private String doctorName;
    private long postalCode;
    private String fieldOfActivity;
    private long patientID;
    private Physician getSelectedPhysician;
    private int selectedPhysicianAfterSearch;


    private int selectAvailabilityID;


    @ManagedProperty("#{searchBean}")
    private SearchBean searchBean;

    public PatientDashboardBean() {
        selectedPhysicianAfterSearch = -1;
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
        return null;
    }

    public void selectAPhysician(int id) {
        Preconditions.checkArgument(id > 0, "The selected physician ID is incorrect");
        this.selectedPhysicianAfterSearch = id;
        PhysicianService physicianService = new PhysicianService();
        getSelectedPhysician = physicianService.findByLongId(Physician.class, selectedPhysicianAfterSearch);
    }

    public int getSelectedPhysicianAfterSearch() {
        return selectedPhysicianAfterSearch;
    }

    public void setSelectedPhysicianAfterSearch(int selectedPhysicianAfterSearch) {
        this.selectedPhysicianAfterSearch = selectedPhysicianAfterSearch;
    }

    public Physician getGetSelectedPhysician() {
        return getSelectedPhysician;
    }

    public void setGetSelectedPhysician(Physician getSelectedPhysician) {
        this.getSelectedPhysician = getSelectedPhysician;
    }

    public int getSelectAvailabilityID() {
        return selectAvailabilityID;
    }

    public void setSelectAvailabilityID(int selectAvailabilityID) {
        this.selectAvailabilityID = selectAvailabilityID;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }
}
