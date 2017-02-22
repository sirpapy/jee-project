package fr.upem.jee.allodoc.faces;


import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.Physician;
import fr.upem.jee.allodoc.service.PatientDashboardService;
import fr.upem.jee.allodoc.service.PatientService;
import fr.upem.jee.allodoc.service.PhysicianService;
import fr.upem.jee.allodoc.utilities.Parser;
import fr.upem.jee.allodoc.utilities.Resources;
import org.primefaces.json.JSONObject;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Sirpapy on 19/01/2017.
 */

@SessionScoped
@ManagedBean(name = "patientDashboadBean", eager = true)
public class PatientDashboardBean {



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
        try (InputStream resourceAsStream = PatientDashboardBean.class.getResourceAsStream(Resources.RESOURCE_XLS_LAPOSTE_HEXASMAL_CSV)) {
            List<Location> locations = Parser.parseCSVPostCode(resourceAsStream);
            for (Location location : locations) {
                toReturn.put(location.getPostalCode(), location.getCity());
            }
            return toReturn;
        }
    }

    public List<Physician> getDoctorResult() throws ParseException {
        try {
            HashMap<Long, HashMap<String,String>> getLatAndLong = init();
            Iterator it = getLatAndLong.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                HashMap<String, String> aLocation = (HashMap<String, String>) pair.getValue();
                double lat = Double.valueOf((String) aLocation.keySet().toArray()[0]);
                double lng = Double.valueOf((String) aLocation.values().toArray()[0]);
                model.addOverlay(new Marker(new LatLng(lat,lng), String.valueOf(pair.getKey())));
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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



    public int getSelectAvailabilityID() {
        return selectAvailabilityID;
    }

    public void setSelectAvailabilityID(int selectAvailabilityID) {
        this.selectAvailabilityID = selectAvailabilityID;
    }


    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }


    public MapModel getModel() {
        return model;
    }

    private MapModel model = new DefaultMapModel();

    public HashMap<Long, HashMap<String, String>> init() throws IOException, ParseException {
        HashMap<String, String> aLocation = new HashMap();
        HashMap<Long, HashMap<String, String>> locations = new HashMap<>();
        List<Physician> physicians = patientDashboardService.getListOfPhysician(searchBean.getName());
        for (Physician physician:physicians) {

            String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address="+physician.getPracticeArea().getCity()+"&key=AIzaSyBnEvpK6QuKAClUMPU-gA4DY1rc7qkkh0k";
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
            JSONObject obj = new JSONObject(response.toString());
            JSONObject obj2 = (JSONObject) obj.getJSONArray("results").get(0);
            Object geometry = obj2.get("geometry");
            Object viewport = ((JSONObject) geometry).get("viewport");
            Object southwest = ((JSONObject) viewport).get("southwest");
            String lat = String.valueOf (((JSONObject) southwest).get("lat"));
            String lng =  String.valueOf (((JSONObject) southwest).get("lng"));
            aLocation.put(lat, lng);
            locations.put(physician.getId(),aLocation);
        }
        return locations;
    }



}
