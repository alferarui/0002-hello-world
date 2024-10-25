package be.abis.twohelloworld.service;


import be.abis.twohelloworld.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> findAllPersons();
    Person findPersonById(int id);
    Person findPersonByEmailAddress(String shortTitle);
    void addPerson(Person c);
    void updatePerson(Person c);
    void deletePerson(int id);
}
