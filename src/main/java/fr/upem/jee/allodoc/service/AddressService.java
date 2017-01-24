package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.entity.Address;
import fr.upem.jee.allodoc.entity.Location;

import java.util.Optional;

/**
 * Created by raptao on 1/24/2017.
 */
public class AddressService extends Service<Address> {
    private final Address address;

    public AddressService(Address address) {
        this.address = address;
    }

    public Optional<Location> existingLocation() {
        return LocationService.getByNamedArea(address.getLocation().getCity());
    }
}
