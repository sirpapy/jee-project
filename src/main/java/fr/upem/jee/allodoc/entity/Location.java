package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

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
    private Integer postalCode;

    private String city;
    private String country;

    public Location() {
    }

    public Location(int postalCode, String city, String country) {
        Preconditions.checkArgument(postalCode > 0, "postalCode must be > 0");
        this.postalCode = postalCode;
        this.city = Preconditions.checkNotNull(city, "city should not be null");
        this.country = Preconditions.checkNotNull(country, "country should not bet null");
    }

    public Location(Integer postalCode, String city) {
        this(postalCode, city, DEFAULT_COUNTRY_NAME);
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
                city.equals(location.city) &&
                country.equals(location.country);
    }


    public static class Builder {
        private int postalCode;
        private String city;
        private String country;

        public Builder setPostalCode(int postalCode) {
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
