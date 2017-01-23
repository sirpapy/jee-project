package fr.upem.jee.allodoc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by raptao on 12/13/2016.
 */
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String streetNumber;
    private String streetName;

    @OneToOne
    private Location location;

    private Address(String streetNumber, String streetName, Location location) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.location = Objects.requireNonNull(location, "location shouldn't be null");
    }

    public Address() {
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
                        Objects.equals(location, address.location);
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRegion(int postalCode, String city, String country) {
        this.location = new Location.Builder().setPostalCode(postalCode).setCity(city).setCountry(country).build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetNumber, streetName, location);
    }

    public static class Builder {
        private String streetNumber;
        private String streetName;
        private Location location;

        public Builder setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Builder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder setLocation(Location location) {
            this.location = location;
            return this;
        }

        public Address createAddress() {
            return new Address(streetNumber, streetName, location);
        }
    }
}
