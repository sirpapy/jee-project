package fr.upem.jee.allodoc.jpa;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

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
        if(postalCode<0)
            throw new IllegalStateException("postalCode must be > 0");
        this.postalCode = postalCode;
        this.city = Objects.requireNonNull(city, "city should not be null");
        this.city = Objects.requireNonNull(country, "country should not be null");
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