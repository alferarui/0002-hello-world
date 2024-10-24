package be.abis.twohelloworld.utilities;

import be.abis.twohelloworld.model.Course;

import java.time.LocalDate;
import java.util.List;

public class MyUtillity {
    public static void printCourse(Course course) {
        System.out.println(course);
    }
    public static void printCourses(List<Course> courses) {
        org.apache.commons.lang3.stream.Streams.of(courses)
                .forEach(System.out::println);
    }
    public static Double getStarDate1970(LocalDate ld){
        int currentYear = ld.getYear();
        int dayOfYear = ld.getDayOfYear();
        int totalDaysInYear = ld.lengthOfYear();
        double dayOfYearFraction = (double) dayOfYear / totalDaysInYear;
        return (currentYear - 1970) + (dayOfYearFraction);
    }

    public static boolean isNullOrEmpty(String obj) {
        return obj == null || obj.isEmpty();
    }
}
