package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by raptao on 12/13/2016.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String streetNumber;
    private String streetName;

    // TODO : la partie en dessous devra être remplacée par une table
    private Integer postalCode;
    private String city;
    private String country;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
