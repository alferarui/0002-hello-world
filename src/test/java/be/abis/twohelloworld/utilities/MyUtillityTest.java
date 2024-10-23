package be.abis.twohelloworld.utilities;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.repository.MemoryCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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