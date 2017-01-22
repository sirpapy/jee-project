package fr.upem.jee.allodoc.faces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Sirpapy on 19/01/2017.
 */
@SessionScoped
@ManagedBean(name = "searchBean", eager = true)
public class SearchBean implements Serializable{

    private String name;
    private String fieldOfActivity;
    private String postalCode;

    private Set<Integer> postalCodeList = PatientDashboardBean.getPostalCodeList().keySet();
    private List<String> RegionList = PatientDashboardBean.getPostalCodeList().values().stream().collect(Collectors.toList());
    private Map<Integer, String> Location = PatientDashboardBean.getPostalCodeList();

    PatientDashboardBean patientDashboardBean = new PatientDashboardBean();

    public SearchBean() throws IOException {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }




    public String startSearch(){
        System.out.println(name);
        System.out.println(fieldOfActivity);
        System.out.println(postalCode);
        return "patientDashBoard";
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
}
