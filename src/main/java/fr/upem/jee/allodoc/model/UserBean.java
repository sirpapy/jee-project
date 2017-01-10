package fr.upem.jee.allodoc.model;

import javax.faces.bean.ManagedBean;

/**
 * Created by raptao on 1/10/2017.
 */
@ManagedBean
public class UserBean {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserBean() {
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String authenticate(){
        if( firstName != null && email != null && password != null){
            return "index";
        }else{
            return "register-form";
        }
    }
}
