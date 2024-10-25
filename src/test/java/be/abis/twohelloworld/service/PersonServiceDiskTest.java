package be.abis.twohelloworld.service;

import be.abis.twohelloworld.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonServiceDiskTest {

    @Autowired
    PersonServiceDisk disk;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initTest(){
        assertNotNull(disk);
    }
    @Test
    void findAllPersonsTest() {
    }

    @Test
    void findPersonByIdTest() {
        Person c = disk.findPersonById(5);
        assertNotNull(c);
    }

    @Test
    void findPersonByShortTitleTest() {
    }

    @Test
    void addPersonTest() {
    }

    @Test
    void updatePersonTest() {
    }

    @Test
    void deletePersonTest() {
    }
}