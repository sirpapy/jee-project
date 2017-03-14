package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Sirpapy on 19/01/2017.
 */
@SessionScoped
@ManagedBean(eager = true)
public class SearchBean implements Serializable {


    private String name;
    private FieldOfActivity fieldOfActivity;
    private Location postalCode;

    private String searchName;
    private FieldOfActivity searchFA;
    private Location searchPostalCode;

    private List<Physician> physicianResultList;
    private Set<Integer> postalCodeList = PatientDashboardBean.getPostalCodeList().keySet();
    private List<String> RegionList = PatientDashboardBean.getPostalCodeList().values().stream().collect(Collectors.toList());
    private Map<Integer, String> Location = PatientDashboardBean.getPostalCodeList();


    public SearchBean() throws IOException {
    }


    public List<Physician> getPhysicianResultList() {
        return physicianResultList;
    }

    public void setPhysicianResultList(List<Physician> physicianResultList) {
        if (this.physicianResultList == null) {
            this.physicianResultList = new ArrayList<>();
        }
        this.physicianResultList = physicianResultList;
    }

    public Map<Integer, String> getLocation() {
        return Location;
    }

    public void setLocation(Map<Integer, String> location) {
        Location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldOfActivity getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Location getPostalCode() {
        return postalCode;
    }


    public void setPostalCode(Location postalCode) {
        this.postalCode = postalCode;
    }

    public String startSearch() throws InterruptedException {
        searchName = name;
        searchFA = fieldOfActivity;
        searchPostalCode = postalCode;
        PhysicianService physicianService = new PhysicianService();
        if (!isSet(searchName) && !searchPostalCode.isSet() && !searchFA.isSet()) { // no value set
            System.out.println("NONE");
            Thread.sleep(5000);
            physicianResultList = PhysicianService.getAll();
        } else if (isSet(searchName) && searchPostalCode.isSet() && searchFA.isSet()) { // everything is set
            System.out.println("ALL");
            Thread.sleep(5000);
            physicianResultList = physicianService.searchByNameFieldOfActivityLocation(searchFA, searchName, searchPostalCode);
        } else if (isSet(searchName) && searchPostalCode.isSet() && !searchFA.isSet()) { // only fieldOfActivity not set
            System.out.println("NAME + POSTALCODE");
            Thread.sleep(5000);
            physicianResultList = physicianService.searchByNameAndLocation(searchName, searchPostalCode);
        } else if (!isSet(searchName) && searchPostalCode.isSet() && searchFA.isSet()) { // only name not set
            System.out.println("FIELD OF ACTIVITY + POSTALCODE");
            Thread.sleep(5000);
            physicianResultList = physicianService.searchByFieldOfActivityAndLocation(searchFA, searchPostalCode);
        } else if (searchPostalCode.isSet() && !isSet(searchName) && !searchFA.isSet()) { // only cp
            System.out.println(" POSTALCODE");
            Thread.sleep(5000);
            physicianResultList = physicianService.searchByLocation(searchPostalCode);
        } else if( isSet(searchName) && !searchPostalCode.isSet() && !searchFA.isSet()){ // onyl
            System.out.println("NAME");
            physicianResultList = physicianService.searchByName(name);
            Thread.sleep(5000);
        }
        searchName = null;
        if (searchFA != null) {
            searchFA.unset();
        }
        if (searchPostalCode != null) {
            searchPostalCode.unset();
        }
        return Resources.PAGE_PATIENT_HOME;
    }

    public Set<Integer> getPostalCodeList() {
        return postalCodeList;
    }

    public void setPostalCodeList(Set<Integer> postalCodeList) {
        this.postalCodeList = postalCodeList;
    }

    public List<String> getRegionList() {
        return RegionList;
    }

    public void setRegionList(List<String> regionList) {
        RegionList = regionList;
    }

    private boolean isSet(String s) {
        return s != null && !s.isEmpty();
    }


}
