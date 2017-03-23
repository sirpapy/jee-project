package fr.upem.jee.allodoc.faces;

/**
 * Created by raptao on 3/23/2017.
 */
public class SearchItem {

    private int postalCode;
    private String physicianName;
    private String fieldOfActivity;

    private SearchItem(int postalCode, String physicianName, String fieldOfActivity) {
        this.postalCode = postalCode;
        this.physicianName = physicianName;
        this.fieldOfActivity = fieldOfActivity;
    }

    public static Builder build(){
        return new Builder();
    }

    private static class Builder {
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
            return new SearchItem(postalCode, physicianName, fieldOfActivity);
        }
    }

}
