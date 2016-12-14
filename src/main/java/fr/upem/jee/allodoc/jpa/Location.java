package fr.upem.jee.allodoc.jpa;

import com.google.common.base.Preconditions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class Location {

    @Id @GeneratedValue
    private Long id;

    private Integer postalCode;
    private String city;
    private String country;

    public Location() {
    }

    public Location(int postalCode, String city, String country) {
        Preconditions.checkArgument(postalCode>0, "postalCode must be > 0");
        this.postalCode = postalCode;
        this.city = Preconditions.checkNotNull(city, "city should not be null");
        this.city = Preconditions.checkNotNull(country, "country should not be null");
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
