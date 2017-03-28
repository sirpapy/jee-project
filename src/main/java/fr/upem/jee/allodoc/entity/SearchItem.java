package fr.upem.jee.allodoc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by raptao on 3/23/2017.
 */
@Entity
public class SearchItem {
    private static String NO_VALUE = "-";

    @Id
    @GeneratedValue
    private long id;

    private Integer postalCode;
    private String physicianName;
    private String fieldOfActivity;
    private LocalDateTime timeStamp;

    public SearchItem(){
    }

    private SearchItem(Integer postalCode, String physicianName, String fieldOfActivity, LocalDateTime timeStamp) {
        this.postalCode = postalCode;
        this.physicianName = physicianName;
        this.fieldOfActivity = fieldOfActivity;
        this.timeStamp = timeStamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode == null ? NO_VALUE : postalCode.toString();
    }

    public String getPhysicianName() {
        return physicianName == null ? NO_VALUE : physicianName;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity == null ? NO_VALUE : fieldOfActivity;
    }

    public boolean isEmptySearch() {
        return postalCode == null && (physicianName == null || physicianName.isEmpty()) && fieldOfActivity == null;
    }

    public String getFormatedTimeStamp() {
        return timeStamp.toString().replace("T", " at ");
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "SearchItem{" +
                "postalCode=" + postalCode +
                ", physicianName='" + physicianName + '\'' +
                ", fieldOfActivity='" + fieldOfActivity + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public static class Builder {

        private Integer postalCode;
        private String physicianName;
        private String fieldOfActivity;

        public Builder setPostalCode(int postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setPhysicianName(String physicianName) {
            this.physicianName = physicianName;
            return this;
        }

        public Builder setFieldOfActivity(String fieldOfActivity) {
            this.fieldOfActivity = fieldOfActivity;
            return this;
        }

        public SearchItem build() {
            return new SearchItem(postalCode, physicianName, fieldOfActivity, LocalDateTime.now());
        }
    }

}
