package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.repository.CsvFileCourseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileCourseRepositoryTest {

    CsvFileCourseRepository repo = new CsvFileCourseRepository();
    void printRepo(){
        System.out.println(" ======== REPO =================================");
        for(Course course:repo.findAllCourses()){
            System.out.println(course);
        }
        System.out.println(" =----------------------------------------------");
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repo.save();
    }

    @Test
    void loadTest() {
        repo.load();
    }

    @Test
    void saveTest() {
        repo.save();
    }

    @Test
    void findAllCoursesTest() {
        List<Course> courses = repo.findAllCourses();
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Test
    void findCourseByIdTest() {
        Course c = repo.findCourseById(2);
        System.out.println(c);
        assertEquals(c.toString(),"Course{courseId=2, shortTitle=\"Assembly Language for Kindergarden\", longTitle=\"Accessible introduction to microcontrollers\", numberOfDays=10, pricePerDay=100}");
        c=repo.findCourseById(10);
        System.out.println(c);
        assertEquals(c.toString(),Course.NULL.toString());

    }

    //@Test
    void findCourseByShortTitleTest() {
        Course c = repo.findCourseByShortTitle("Bricolage");
        System.out.println(c);
        assertEquals("Course{courseId=null, shortTitle=null, longTitle=null, numberOfDays=null, pricePerDay=null}",c.toString());
        c = repo.findCourseByShortTitle("Bricolage*");
        System.out.println(c);
        assertEquals(c.toString(),Course.NULL.toString());
    }

    @Test
    void addCourseTest() {
        Course c = new Course(
                repo.findAllCourses().size()+1,
                "tiptoeing around cats",
                "learn how to move slowly and in silence",
                5,
                50
        );
        printRepo();
        repo.addCourse(c);
        printRepo();
        repo.deleteCourse(c.getCourseId());
        printRepo();
    }

    @Test
    void updateCourseTest() {
        Course c = new Course(
                repo.findAllCourses().size()+1,
                "tiptoeing around cats",
                "learn how to move slowly and in silence",
                5,
                50
        );
        printRepo();
        repo.addCourse(c);
        printRepo();
        c.setNumberOfDays(7);
        repo.updateCourse(c);
        printRepo();

        repo.deleteCourse(c.getCourseId());
        printRepo();
    }

    @Test
    void deleteCourseTest() {
        Course c = new Course(
                repo.findAllCourses().size()+1,
                "tiptoeing around cats",
                "learn how to move slowly and in silence",
                5,
                50
        );
        printRepo();
        repo.addCourse(c);
        printRepo();
        repo.deleteCourse(c.getCourseId());
        printRepo();
    }
}