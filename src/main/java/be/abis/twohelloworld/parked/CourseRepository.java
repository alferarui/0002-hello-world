package be.abis.twohelloworld.parked;

import be.abis.twohelloworld.model.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAllCourses();
    Course findCourseById(int id);
    Course findCourseByShortTitle(String shortTitle);
    List<Course> findCoursesByDuration(int duration);
    void addCourse(Course c);
    void updateCourse(Course c);
    void deleteCourse(int id);
}
