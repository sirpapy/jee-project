package fr.upem.jee.allodoc.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Sirpapy on 18/01/2017.
 */
@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class PageNavigationBean  implements Serializable {

    @ManagedProperty(value="#{param.page}")
    private String page;


    public String getToPage(){
        if (page.equals("home")){
            System.out.println("Going to Home");
            return "home";
        }
        return "home";
    }
}
