package be.abis.twohelloworld.controller;


import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.service.CourseServiceDisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoursesApiController {

    @Autowired
    CourseServiceDisk courseService;

    @GetMapping(path="/courses/query")
    Course findCourse(@RequestParam("title") String title) {
        return courseService.findCourseByShortTitle(title);
    }
    @GetMapping(path="/courses")
    List<Course> all() {
        return courseService.findAllCourses();
    }
}
