
package be.abis.csvmagic.repository;

import be.abis.twohelloworld.model.Address;

import java.util.List;
import java.util.function.Predicate;

/**
 * generated source
 **/
interface AddressRepository {
    // Add an entity to the repository
    void add(Address ent);
    // Remove an entity from the repository
    void remove(Address ent);
    // Update an entity in the repository
    void update(Address ent);
    // Find entities using a lambda (predicate);
    List<Address> find(Predicate<? super Address> predicate);
    // Match entities using a regular expression on all fields (full-text search)
    List<Address> match(String regexpString);
}

