package be.abis.twohelloworld.service;


import be.abis.twohelloworld.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> findAllCourses();
    Course findCourseById(int id);
    Course findCourseByShortTitle(String shortTitle);
    void addCourse(Course c);
    void updateCourse(Course c);
    void deleteCourse(int id);
}
