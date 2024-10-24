package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Person;
import be.abis.twohelloworld.repository.PersonCsvStorageRepository;
import be.abis.twohelloworld.repository.PersonMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;


@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonMemoryRepository memory;
    @Autowired
    private PersonCsvStorageRepository csv;

    @BeforeEach
    void setUp() {
        System.out.println(memory);
        System.out.println(csv);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() {
        assert memory != null;
        assert csv != null;
        assert memory.count() == 0;
        assert csv.count() == 5;
    }


    @Test
    void add() {

    }

    @Test
    void remove() {
    }

    @Test
    void update() {
    }

    @Test
    void find() {
        Person p = csv.find(pers -> pers.getPersonId() == 1).get(0);
        System.out.println(p);
        assert p.getPersonId() == 1;
        p = csv.find(pers -> pers.getPersonId() == 5).get(0);
        System.out.println(p);
        assert p.getPersonId() == 5;
    }

    @Test
    void match() {
    }
}