package fr.upem.jee.allodoc.faces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by raptao on 1/21/2017.
 */
@ManagedBean
@SessionScoped
public class ConnectedUserBean {

    private String connectedUsername;

    public String getConnectedUsername() {
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public String load() {

        return "";
    }
}
