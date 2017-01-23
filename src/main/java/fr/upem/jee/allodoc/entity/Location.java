package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findByPostalCode",
                query = "Select l from Location l where l.postalCode = :pc"),
        @NamedQuery(name = "findLocationByRegion",
                query = "Select l from Location  l where l.city = :region")
})
public class Location implements Serializable {

    private static final String DEFAULT_COUNTRY_NAME = "France";
    @Id
    @GeneratedValue


    private long id;
    private Integer postalCode;
    private String city;
    private String country;
    public Location() {
    }

    public Location(int postalCode, String city, String country) {
        Preconditions.checkArgument(postalCode > 0, "postalCode must be > 0 : yours :" + postalCode);
        this.postalCode = postalCode;
        this.city = Preconditions.checkNotNull(city, "city should not be null");
        this.country = Preconditions.checkNotNull(country, "country should not bet null");
    }

    public Location(Integer postalCode, String city) {
        this(postalCode, city, DEFAULT_COUNTRY_NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, city, country);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || !(o instanceof Location)) return false;

        Location location = (Location) o;

        return postalCode.equals(location.postalCode) &&
                cleanCityString(city).equals(cleanCityString(location.city)) &&
                country.equals(location.country);
    }

    private String cleanCityString( String city ){
        return city.replace("-", " ").toUpperCase();
    }

    public static class Builder {
        private int postalCode;
        private String city;
        private String country;

        public Builder setPostalCode(int postalCode) {
            Preconditions.checkArgument(postalCode>0, "postal code is < 0 :: "+postalCode);
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Location build() {
            if (country == null) {
                return new Location(postalCode, city);
            }
            return new Location(postalCode, city, country);
        }
    }
}
