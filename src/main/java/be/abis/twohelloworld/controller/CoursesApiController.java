package be.abis.twohelloworld.controller;


import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.service.CourseServiceDisk;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
public class CoursesApiController {

    @Autowired
    CourseServiceDisk courseService;

    @GetMapping(path="/courses/query")
    Course findCourse(@RequestParam("title") String title) {
        return courseService.findCourseByShortTitle(title);
    }

    @GetMapping(path="/courses")
    public List<Course> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping(path="/courses/{id}")
    public Course findCourseById(@PathParam("id") int id) {
        return courseService.findCourseById(id);
    }

    @PostMapping(path="/courses")
    public ResponseEntity<Course> addCourse(@RequestBody HashMap<String,String> c) {
        // TODO migrate the creation / validation part in the service or even repository as it needs to generate an new CourseId
        HashMap<String,String> errors=new HashMap<String,String>();
        Course course = new Course();

        course.setCourseId(courseService.findAllCourses().size() + 1);
        //try{course.setCourseId(Integer.parseInt(c.get("courseId")));}catch(Exception x){ errors.put("courseId",x.getMessage());}
        try{course.setShortTitle(c.get("shortTitle"));}catch(Exception x){ errors.put("shortTitle",x.getMessage());}
        try{course.setLongTitle(c.get("longTitle"));}catch(Exception x){ errors.put("longTitle",x.getMessage());}
        try{course.setNumberOfDays(Integer.parseInt(c.get("numberOfDays")));}catch(Exception x){ errors.put("numberOfDays",x.getMessage());}
        try{course.setPricePerDay(Integer.parseInt(c.get("pricePerDay")));}catch(Exception x){ errors.put("pricePerDay",x.getMessage());}

        if(errors.isEmpty()){
            courseService.addCourse(course);
            return ResponseEntity.ok(course);
        }else {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/courses")
    public ResponseEntity<Course> updateCourse(@RequestBody HashMap<String,String> c) {

        // TODO migrate the creation / validation part in the service or even repository as it needs to generate an new CourseId
        HashMap<String,String> errors=new HashMap<String,String>();
        Course course = new Course();
        try{course.setCourseId(Integer.parseInt(c.get("courseId")));}catch(Exception x){ errors.put("courseId",x.getMessage());}
        try{course.setShortTitle(c.get("shortTitle"));}catch(Exception x){ errors.put("shortTitle",x.getMessage());}
        try{course.setLongTitle(c.get("longTitle"));}catch(Exception x){ errors.put("longTitle",x.getMessage());}
        try{course.setNumberOfDays(Integer.parseInt(c.get("numberOfDays")));}catch(Exception x){ errors.put("numberOfDays",x.getMessage());}
        try{course.setPricePerDay(Integer.parseInt(c.get("pricePerDay")));}catch(Exception x){ errors.put("pricePerDay",x.getMessage());}

        if(errors.isEmpty()){
            courseService.addCourse(course);
            return ResponseEntity.ok(course);
        }else {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path="/courses")
    public HashMap<String,String> deleteCourse(int id) {
        courseService.deleteCourse(id);
        return new HashMap<String,String>(){{
            put("deleted",Integer.valueOf(id).toString());
        }};
    }
}
