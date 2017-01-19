package fr.upem.jee.allodoc.model;

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
public class PageNavigationBean  implements Serializable {

    public String getToPage(String page){
        if (page.equals("home")){
            System.out.println("Going to Home");
            return "home";
        }
        return "home";
    }



    /*
    * Return the list of Field of Activity
    * */
    public List<String> getFieldOfActivity(){
        return new ArrayList<String>() {{
            add("Generaliste");
            add("Gynéco");
            add("Neurologue");
        }};
    }


}
