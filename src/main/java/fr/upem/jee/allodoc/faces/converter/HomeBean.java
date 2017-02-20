package fr.upem.jee.allodoc.faces.converter;

import fr.upem.jee.allodoc.faces.ConnectedUserBean;
import fr.upem.jee.allodoc.utilities.Resources;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.spi.Bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sirpapy on 14/02/2017.
 */
@ManagedBean
@SessionScoped
public class HomeBean {

    private ConnectedUserBean bean;
    @PostConstruct
    public void load() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        bean = (ConnectedUserBean) req.getSession().getAttribute("connectedUserBean");

    }

    public String showProfil() {
        if (!bean.isPatient())
            return Resources.PAGE_PHYSICIAN_PROFIL;
        return Resources.PAGE_PATIENT_PROFIL;
    }

}
