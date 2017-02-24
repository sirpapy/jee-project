package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.service.FieldOfActivityService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by raptao on 2/24/2017.
 */
@ManagedBean
@ApplicationScoped
public class DataContainerBean {
    private List<FieldOfActivity> fieldOfActivities;

    public DataContainerBean() {
        fieldOfActivities = FieldOfActivityService.getAll();
    }

    public List<FieldOfActivity> getFieldOfActivities() {
        return fieldOfActivities;
    }

    public void setFieldOfActivities(List<FieldOfActivity> fieldOfActivities) {
        this.fieldOfActivities = fieldOfActivities;
    }

}
