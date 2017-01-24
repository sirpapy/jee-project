package fr.upem.jee.allodoc.faces;


import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Pages;
import fr.upem.jee.allodoc.utilities.Parser;

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


    public static final String RESSOURCE_XLS_LAPOSTE_HEXASMAL_CSV = "/XLS/laposte_hexasmal.csv";
    PatientDashboardService patientDashboardService = new PatientDashboardService();
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
        try (InputStream resourceAsStream = PatientDashboardBean.class.getResourceAsStream(RESSOURCE_XLS_LAPOSTE_HEXASMAL_CSV)) {
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

    public void selectAPhysician(int id) {
        Preconditions.checkArgument(id > 0, "The selected physician ID is incorrect");
        this.selectedPhysicianAfterSearch = id;
        Preconditions.checkArgument(selectedPhysicianAfterSearch > 0, "You didn't selected a physician");
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

    public String validateAppointment() {
        System.out.println(patientID);
        System.out.println(selectAvailabilityID);
        PatientService patientService = new PatientService();
        Patient patient = new Patient();
        patientService.save(patient);
        Patient lePatient = patientService.getFromId(patientID);
        patientService.takeControl(lePatient);
        patientService.setNewAppointment(getSelectedPhysician, selectAvailabilityID, selectAvailabilityID);
        return Pages.PAGE_INDEX;
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
