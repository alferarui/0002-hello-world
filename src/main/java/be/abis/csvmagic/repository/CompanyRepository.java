
package be.abis.csvmagic.repository;

import be.abis.twohelloworld.model.Company;

import java.util.List;
import java.util.function.Predicate;

/**
 * generated source
 **/
interface CompanyRepository {
    // Add an entity to the repository
    void add(Company ent);
    // Remove an entity from the repository
    void remove(Company ent);
    // Update an entity in the repository
    void update(Company ent);
    // Find entities using a lambda (predicate);
    List<Company> find(Predicate<? super Company> predicate);
    // Match entities using a regular expression on all fields (full-text search)
    List<Company> match(String regexpString);
}

