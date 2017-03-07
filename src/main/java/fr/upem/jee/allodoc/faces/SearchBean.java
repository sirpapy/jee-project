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

    public String startSearch() {
        PhysicianService physicianService = new PhysicianService();
        if (!isSet(name) && !postalCode.isSet() && !fieldOfActivity.isSet()) { // no value set
            physicianResultList = PhysicianService.getAll();
        } else if (isSet(name) && postalCode.isSet() && fieldOfActivity.isSet()) { // everything is set
            physicianResultList = physicianService.searchByNameFieldOfActivityLocation(fieldOfActivity, name, postalCode);
        } else if (isSet(name) && postalCode.isSet() && !fieldOfActivity.isSet()) { // only fieldOfActivity not set
            physicianResultList = physicianService.searchByNameAndLocation(name, postalCode);
        } else if (!isSet(name) && postalCode.isSet() && fieldOfActivity.isSet()) { // only name not set
            physicianResultList = physicianService.searchByFieldOfActivityAndLocation(fieldOfActivity, postalCode);
        } else if (postalCode.isSet() && !isSet(name) && !fieldOfActivity.isSet()) { // only cp
            physicianResultList = physicianService.searchByLocation(postalCode);
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
