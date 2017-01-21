package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.utilities.Pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sirpapy on 18/01/2017.
 */
@ManagedBean(name = "navigationBean", eager = true)
@RequestScoped
public class PageNavigationBean implements Serializable {


    @ManagedProperty("#{searchBean}")
    SearchBean searchBean;

    public String goToHome() {
        return Pages.PAGE_HOME_SEARCH;
    }


    public List<String> getFieldOfActivity() {
        return new ArrayList<String>() {{
            add("Generaliste");
            add("Gynéco");
            add("Neurologue");
        }};
    }

    public SearchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

}
