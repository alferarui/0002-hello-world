package be.abis.twohelloworld.utilities;

import be.abis.twohelloworld.model.Course;

import java.util.List;

public class MyUtillity {
    public static void printCourse(Course course) {
        System.out.println(course);
    }
    public static void printCourses(List<Course> courses) {
        org.apache.commons.lang3.stream.Streams.of(courses)
                .forEach(System.out::println);
    }
}
