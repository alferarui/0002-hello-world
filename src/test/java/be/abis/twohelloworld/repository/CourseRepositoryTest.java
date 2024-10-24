package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static be.abis.twohelloworld.utilities.MyUtillity.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    public static Course getRandomCourse(Integer id) {
        return new Course(){{
            Integer randomSubjectId = new Random().nextInt(descriptions.length);
            setCourseId(id);
            setShortTitle(titles[randomSubjectId]);
            setLongTitle(descriptions[randomSubjectId]);
            setNumberOfDays(randomItem(new Integer[]{2,5,7,9,11,14}));
            setPricePerDay(randomItem(new Integer[]{7,11,23,25,99,128}));
        }};
    }
    @Autowired
    CourseMemoryRepository memory;
    @Autowired
    CourseCsvStorageRepository csv;

    @BeforeEach
    void setUp() {
        System.out.println(memory);
        System.out.println(csv);
        for(Long k : range(1,10,1)) {
            Course randomCourse = getRandomCourse(memory.count()+1);
            memory.add(randomCourse);
        }
        memory.all().forEach(System.out::println);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initTestMemory() {
        assertNotNull(memory);
        assertNotNull(csv);
        assertTrue( memory.count()>=5);
        assertTrue( csv.count()>=5);
    }

    @Test
    void addTestMemory() {
        Course randomCourse = getRandomCourse(memory.count()+1);
        System.out.println(randomCourse);
        System.out.println(memory.count());
        memory.add(randomCourse);
        System.out.println(memory.count());
    }
    @Test
    void addTestCsv() {
        Course randomCourse = getRandomCourse(csv.count()+1);
        System.out.println(randomCourse);
        System.out.println(csv.count());
        csv.add(randomCourse);
        System.out.println(csv.count());
    }

    @Test
    void remove() {
        Course randomCourse = getRandomCourse(csv.count()+1);
        System.out.println(randomCourse);
        System.out.println(csv.count());
        csv.add(randomCourse);
        System.out.println(csv.count());
    }

    @Test
    void update() {
        System.out.println(memory.count());
        memory.all().forEach(p0 -> System.out.println(" - " + p0.toString()));
        Course p = memory.find(pers -> pers.getCourseId() == 1).get(0);
        p.setCourseId(100);
        memory.update(p);
        System.out.println(memory.count());
        memory.all().forEach(p0 -> System.out.println(" - " + p0.toString()));
    }

    @Test
    void findTestMemory() {
        Course p = memory.find(pers -> pers.getCourseId() == 1).get(0);
        System.out.println(p);
        assert p.getCourseId() == 1;
        p = memory.find(pers -> pers.getCourseId() == 5).get(0);
        System.out.println(p);
        assert p.getCourseId() == 5;
    }

    @Test
    void matchTestMemory() {
        String regex = ".*?(Dodging|talking|becoming|Couch).*?";
        List<Course> p = memory.match(regex);
        System.out.println(regex);
        p.forEach(p0 -> System.out.println(" - " + p0.toString()));
        regex = ".*?(Certified|Procrastination|Chores).*?";
        p = memory.match(regex);
        System.out.println(regex);
        p.forEach(p0 -> System.out.println(" - " + p0.toString()));
    }
}