
package be.abis.twohelloworld.model;

import java.util.List;
import java.util.function.Predicate;

/**
 * generated source
 **/
interface PersonRepository {
    // Add an entity to the repository
    void add(Person ent);
    // Remove an entity from the repository
    void remove(Person ent);
    // Update an entity in the repository
    void update(Person ent);
    // Find entities using a lambda (predicate);
    List<Person> find(Predicate<? super Person> predicate);
    // Match entities using a regular expression on all fields (full-text search)
    List<Person> match(String regexpString);
}

