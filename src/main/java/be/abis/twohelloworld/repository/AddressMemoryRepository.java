
package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * @apiNote generated using generator.java.class_inmem_repository.groovy generator
 *          Target entity be.abis.twohelloworld.model.Address
 *          Entity Fields 
 *           - (Id) street
 *           - (Id) nr
 *           - (Id) zipCode
 *           - addressId
 *           - town
 */
class AddressMemoryRepository implements AddressRepository{
    private final ArrayList<Address> storage = new ArrayList<>();
    public List<Address> all(){
        return storage;
    }
    public AddressMemoryRepository(){}

    // Add an entity to the repository
    public void add(Address ent) {
        if (storage.stream().noneMatch(e -> matchesId(e, ent))) {
            storage.add(ent);
        }
    }

    // Remove an entity from the repository
    public void remove(Address ent) {
        storage.removeIf ( e -> matchesId(e, ent) );
    }

    // Update an entity in the repository
    public void update(Address ent) {
        var exists = storage.stream().anyMatch(e -> matchesId(e, ent));
        if(exists) {
            storage.removeIf(address -> matchesId(address, ent));
            storage.add(ent);
        }
    }

    // Find entities using a lambda (predicate)
    public List<Address> find(Predicate<? super Address> predicate) {
        return storage.stream().filter ( predicate ).toList();
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Address> match(String regexpString) {
        return storage.stream().filter(address -> stringify(address).matches(regexpString)).toList();
    }

    public void clear(){
        storage.clear();
    }

    private String stringify(Address address) {
        return (address.getStreet())+ "$" +(address.getNr())+ "$" +(address.getZipCode())+ "$" +(address.getAddressId())+ "$" +(address.getTown());
    }
    private String stringifyId(Address address) {
        return (address.getStreet())+ "$" +(address.getNr())+ "$" +(address.getZipCode());
    }

    // Helper method to match entities by ID fields
    private boolean matchesId(Address address1, Address address2) {
        return stringifyId(address1).equals(stringifyId(address2));
    }
}
