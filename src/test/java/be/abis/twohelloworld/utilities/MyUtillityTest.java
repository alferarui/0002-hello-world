package be.abis.twohelloworld.utilities;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.parked.MemoryCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyUtillityTest {
    MemoryCourseRepository memoryCourseRepository = new MemoryCourseRepository();
    @BeforeEach
    void beforeEach(){

    }

    @Test
    void printCourseTest() {
        MyUtillity.printCourse(memoryCourseRepository.findAllCourses().stream().findFirst().orElse(Course.NULL));
    }

    @Test
    void printCoursesTest() {
        MyUtillity.printCourses(memoryCourseRepository.findAllCourses());
    }
}