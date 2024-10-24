package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static be.abis.twohelloworld.utilities.MyUtillity.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PersonRepositoryTest {

    public static Person getRandomPerson(Integer id) {
        return new Person(){{
            setPersonId(id);
            setFirstName(randomCommonName());
            setLastName(randomFamilyName());
            setBirthday(randomLocalDate("1970-01-01","2005-12-31"));
            setHomeAddress(randomStreetName());
            setLanguage(randomLanguageId());
            setEmailAddress(String.format("%s.%s@%s.%s",getFirstName(),getLastName(),randomCompanyName(),randomDomainTLD()));
            setPhone(getRandomPhoneNumber());
            setMobile(getRandomPhoneNumber());
            setStreet(randomStreetName());
            setNumber(Integer.valueOf(new Random().nextInt(900) + 100).toString());
            setZipCode(getRandomZipCode());
            setCity(randomCity());
        }};
    }

    @Autowired
    private PersonMemoryRepository memory;
    @Autowired
    private PersonCsvStorageRepository csv;

    @BeforeEach
    void setUp() {
        System.out.println(memory);
        System.out.println(csv);
        for(Long k : range(1,10,1)) {
            Person randomPerson = getRandomPerson(memory.count()+1);
            memory.add(randomPerson);
        }
        System.out.println(memory.all());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initTestMemory() {
        assertNotNull(memory);
        assertNotNull(csv);
        assertTrue( memory.count()>=10);
        assertTrue( csv.count()>=5);
    }


    @Test
    void addTestMemory() {
        Person randomPerson = getRandomPerson(memory.count()+1);
        System.out.println(randomPerson);
        System.out.println(memory.count());
        memory.add(randomPerson);
        System.out.println(memory.count());
    }
    @Test
    void addTestCsv() {
        Person randomPerson = getRandomPerson(csv.count()+1);
        System.out.println(randomPerson);
        System.out.println(csv.count());
        csv.add(randomPerson);
        System.out.println(csv.count());
    }

    @Test
    void removeTestMemory() {
        System.out.println(memory.count());
        Person p = memory.find(pers -> pers.getPersonId() == 1).get(0);
        memory.remove(p);
        System.out.println(memory.count());
    }

    @Test
    void updateTestMemory() {
        System.out.println(memory.count());
        memory.all().forEach(p0 -> System.out.println(" - " + p0.toString()));
        Person p = memory.find(pers -> pers.getPersonId() == 1).get(0);
        p.setPersonId(100);
        memory.update(p);
        System.out.println(memory.count());
        memory.all().forEach(p0 -> System.out.println(" - " + p0.toString()));
    }

    @Test
    void findTestMemory() {
        Person p = memory.find(pers -> pers.getPersonId() == 1).get(0);
        System.out.println(p);
        assert p.getPersonId() == 1;
        p = memory.find(pers -> pers.getPersonId() == 5).get(0);
        System.out.println(p);
        assert p.getPersonId() == 5;
    }

    @Test
    void matchTestMemory() {
        String regex = ".*?(Patricia|Taylor|Jackson|William).*?";
        List<Person> p = memory.match(regex);
        System.out.println(regex);
        p.forEach(p0 -> System.out.println(" - " + p0.toString()));
        regex = ".*?(Facebook|Apple|Google).*?";
        p = memory.match(regex);
        System.out.println(regex);
        p.forEach(p0 -> System.out.println(" - " + p0.toString()));
    }
}