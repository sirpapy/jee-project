package fr.upem.jee.allodoc.faces.converter;

import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.faces.DataContainerBean;
import fr.upem.jee.allodoc.service.FieldOfActivityService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Optional;

/**
 * Created by raptao on 1/21/2017.
 */
@FacesConverter("fieldOfActivityConverter")
public class FieldOfActivityConverter implements Converter {
    private static final String DEFAULT_FIELD_OF_ACTIVITY = "Field of activity";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Optional<FieldOfActivity> byName = FieldOfActivityService.getByName(value);
        // returning field of activity from database
        if (byName.isPresent()) {
            return byName.get();
        }
        // saving the new field of activity if not in database
        FieldOfActivity fieldOfActivity = new FieldOfActivity(value);
        FieldOfActivityService.distinctSave(fieldOfActivity);
        return fieldOfActivity;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        FieldOfActivity fieldOfActivity = (FieldOfActivity) value;
        return fieldOfActivity.getName().equals(DataContainerBean.VALUE_NOT_SET) ? ((FieldOfActivity) value).getName() : "ERROR:VALUE";
    }
}
