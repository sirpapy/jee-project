package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by raptao on 12/13/2016.
 */
@Entity
public class Address{

    @Id @GeneratedValue
    private Long id;
    private String streetNumber;
    private String streetName;

    // TODO : la partie en dessous devra être remplacée par une table
    private Integer postalCode;
    private String city;
    private String country;


    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address() throws IOException {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) ||
                Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(streetName, address.streetName) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetNumber, streetName, postalCode, city, country);
    }
}
