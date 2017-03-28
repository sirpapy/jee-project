package fr.upem.jee.allodoc.faces.patient;

import fr.upem.jee.allodoc.entity.Patient;
import fr.upem.jee.allodoc.entity.SearchItem;
import fr.upem.jee.allodoc.faces.ConnectedUserBean;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by raptao on 3/28/2017.
 */
@ManagedBean
@RequestScoped
public class SearchChart {

    private PieChartModel pieChart;
    @ManagedProperty("#{connectedUserBean}")
    private ConnectedUserBean connectedUserBean;

    public PieChartModel getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChartModel pieChart) {
        this.pieChart = pieChart;
    }

    @PostConstruct
    public void init() {
        initChartFromUserData(connectedUserBean.getConnectedPatient());
    }


    public ConnectedUserBean getConnectedUserBean() {
        return connectedUserBean;
    }

    public void setConnectedUserBean(ConnectedUserBean connectedUserBean) {
        this.connectedUserBean = connectedUserBean;
    }

    private PieChartModel initChartFromUserData(Patient connectedPatient) {
        pieChart = new PieChartModel();
        List<SearchItem> searchItems = connectedPatient.getSearchItems();
        pieChart.setTitle("Number of physician/department");
        pieChart.setLegendPosition("w");
        Map<String, Long> collect = searchItems.stream()
                .map(item -> {
                    String postalCode = item.getPostalCode();
                    String dep = postalCode.substring(0, 1) + "000";
                    return SearchItem.builder()
                            .setPostalCode(Integer.parseInt(dep))
                            .setPhysicianName(item.getPhysicianName())
                            .setFieldOfActivity(item.getFieldOfActivity()).build();
                })
                .collect(Collectors.groupingBy(SearchItem::getPostalCode, Collectors.counting()));
        collect.forEach((key, value) -> pieChart.set(key, value));
        return pieChart;
    }
}
