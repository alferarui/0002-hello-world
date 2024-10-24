
package be.abis.csvmagic.repository;

import be.abis.twohelloworld.model.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * @apiNote generated using generator.java.class_inmem_repository.groovy generator
 *          Target entity be.abis.twohelloworld.model.Company
 *          Entity Fields 
 *           - (Id) vatNr
 *           - companyId
 *           - name
 *           - telephoneNumber
 */
class CompanyMemoryRepository implements CompanyRepository{
    private final ArrayList<Company> storage = new ArrayList<>();
    public List<Company> all(){
        return storage;
    }
    public CompanyMemoryRepository(){}

    // Add an entity to the repository
    public void add(Company ent) {
        if (storage.stream().noneMatch(e -> matchesId(e, ent))) {
            storage.add(ent);
        }
    }

    // Remove an entity from the repository
    public void remove(Company ent) {
        storage.removeIf ( e -> matchesId(e, ent) );
    }

    // Update an entity in the repository
    public void update(Company ent) {
        var exists = storage.stream().anyMatch(e -> matchesId(e, ent));
        if(exists) {
            storage.removeIf(company -> matchesId(company, ent));
            storage.add(ent);
        }
    }

    // Find entities using a lambda (predicate)
    public List<Company> find(Predicate<? super Company> predicate) {
        return storage.stream().filter ( predicate ).toList();
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Company> match(String regexpString) {
        return storage.stream().filter(company -> stringify(company).matches(regexpString)).toList();
    }

    public void clear(){
        storage.clear();
    }

    private String stringify(Company company) {
        return (company.getVatNr())+ "$" +Long.valueOf(company.getCompanyId())+ "$" +(company.getName())+ "$" +(company.getTelephoneNumber());
    }
    private String stringifyId(Company company) {
        return (company.getVatNr());
    }

    // Helper method to match entities by ID fields
    private boolean matchesId(Company company1, Company company2) {
        return stringifyId(company1).equals(stringifyId(company2));
    }
}
