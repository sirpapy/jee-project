package fr.upem.jee.allodoc.jpa;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by raptao on 12/14/2016.
 */
@Embeddable
public class PhysicianAvailabilityKey implements Serializable {
    private Long physician;
    private Long availability;
}
