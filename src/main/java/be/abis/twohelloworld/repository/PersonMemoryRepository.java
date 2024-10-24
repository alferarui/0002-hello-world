
package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * @apiNote generated using generator.java.class_inmem_repository.groovy generator
 *          Target entity be.abis.twohelloworld.model.Person
 *          Entity Fields 
 *           - (Id) emailAddress
 *           - personId
 *           - firstName
 *           - lastName
 *           - birthday
 *           - homeAddress
 *           - language
 *           - phone
 *           - mobile
 *           - street
 *           - number
 *           - zipCode
 *           - city
 */
@Repository
class PersonMemoryRepository implements PersonRepository{
    private final ArrayList<Person> storage = new ArrayList<>();
    public List<Person> all(){
        return storage;
    }
    public PersonMemoryRepository(){}

    // Add an entity to the repository
    public void add(Person ent) {
        if (storage.stream().noneMatch(e -> matchesId(e, ent))) {
            storage.add(ent);
        }
    }

    // Remove an entity from the repository
    public void remove(Person ent) {
        storage.removeIf ( e -> matchesId(e, ent) );
    }

    // Update an entity in the repository
    public void update(Person ent) {
        var exists = storage.stream().anyMatch(e -> matchesId(e, ent));
        if(exists) {
            storage.removeIf(person -> matchesId(person, ent));
            storage.add(ent);
        }
    }

    // Find entities using a lambda (predicate)
    public List<Person> find(Predicate<? super Person> predicate) {
        return storage.stream().filter ( predicate ).toList();
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Person> match(String regexpString) {
        return storage.stream().filter(person -> stringify(person).matches(regexpString)).toList();
    }

    @Override
    public int count() {
        return storage.size();
    }

    public void clear(){
        storage.clear();
    }

    private String stringify(Person person) {
        return (person.getEmailAddress())+ "$" +(person.getPersonId())+ "$" +(person.getFirstName())+ "$" +(person.getLastName())+ "$" +(person.getBirthday())+ "$" +(person.getHomeAddress())+ "$" +(person.getLanguage())+ "$" +(person.getPhone())+ "$" +(person.getMobile())+ "$" +(person.getStreet())+ "$" +(person.getNumber())+ "$" +(person.getZipCode())+ "$" +(person.getCity());
    }
    private String stringifyId(Person person) {
        return (person.getEmailAddress());
    }

    // Helper method to match entities by ID fields
    private boolean matchesId(Person person1, Person person2) {
        return stringifyId(person1).equals(stringifyId(person2));
    }
}
