package be.abis.twohelloworld.service;

import be.abis.twohelloworld.model.Person;
import be.abis.twohelloworld.repository.PersonCsvStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceDisk implements PersonService{

    @Autowired
    PersonCsvStorageRepository storageRepository;

    @Override
    public List<Person> findAllPersons() {
        return storageRepository.all();
    }

    @Override
    public Person findPersonById(int id) {
        return storageRepository.find(c -> c.getPersonId() == id).get(0);
    }

    public Person findPersonByEmailAddress(String shortTitle) {
        return storageRepository.find(c -> c.getEmailAddress().equals(shortTitle)).get(0);
    }

    @Override
    public void addPerson(Person c) {
        storageRepository.add(c);
    }

    @Override
    public void updatePerson(Person c) {
        storageRepository.update(c);
    }

    @Override
    public void deletePerson(int id) {
        Person Person = storageRepository.find(c -> c.getPersonId() == id).get(0);
        storageRepository.remove(Person);
    }
}
