package be.abis.twohelloworld.utilities;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.parked.MemoryCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.abis.twohelloworld.utilities.MyUtillity.randomWord;

class MyUtillityTest {
    MemoryCourseRepository memoryCourseRepository = new MemoryCourseRepository();
    @BeforeEach
    void beforeEach(){

    }

    @Test
    void printCourseTestTest() {
        MyUtillity.printCourse(memoryCourseRepository.findAllCourses().stream().findFirst().orElse(Course.NULL));
    }

    @Test
    void printCoursesTestTest() {
        MyUtillity.printCourses(memoryCourseRepository.findAllCourses());
    }

    @Test
    void randomVowelTest() {
    }

    @Test
    void randomConsonantTest() {
    }

    @Test
    void randomSyllableTest() {
    }

    @Test
    void rangeTest() {
    }

    @Test
    void randomWordTest() {
        System.out.println(randomWord(3));
        System.out.println(randomWord(4));
        System.out.println(randomWord(5));
        System.out.println(randomWord(6));
        System.out.println(randomWord(7));
        System.out.println(randomWord(8));
    }
}