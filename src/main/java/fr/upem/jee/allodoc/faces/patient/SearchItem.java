package fr.upem.jee.allodoc.faces.patient;

import java.time.LocalDateTime;

/**
 * Created by raptao on 3/23/2017.
 */
public class SearchItem {

    private Integer postalCode;
    private String physicianName;
    private String fieldOfActivity;
    private LocalDateTime timeStamp;

    private SearchItem(Integer postalCode, String physicianName, String fieldOfActivity, LocalDateTime timeStamp) {
        this.postalCode = postalCode;
        this.physicianName = physicianName;
        this.fieldOfActivity = fieldOfActivity;
        this.timeStamp = timeStamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public boolean isEmptySearch() {
        return postalCode == null && (physicianName == null || physicianName.isEmpty()) && fieldOfActivity == null;
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

    static class Builder {
        private int postalCode;
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
