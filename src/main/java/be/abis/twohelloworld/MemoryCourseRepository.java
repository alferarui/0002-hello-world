package be.abis.twohelloworld;

import be.abis.twohelloworld.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemoryCourseRepository implements CourseRepository {
    ArrayList<Course> courses = new ArrayList<>(){{
        add(new Course(1L,"Korean Kitchen","Basics of Korean Kitchen",10,100));
        add(new Course(2L,"Assembly Language for Kindergarden","Accessible introduction to microcontrollers",10,100));
        add(new Course(3L,"Around the World in 80 days","Meditation",10,100));
        add(new Course(4L,"Chinese","introductory course to Chinese Writing",10,100));
        add(new Course(6L,"Bricolage","the basics of household maintenance",10,100));
    }};

    @Override
    public List<Course> findAllCourses() {
        return courses
    }

    @Override
    public Course findCourseById(int id) {
        return courses.stream().filter(c -> c.getCourseId() == id).findFirst().orElse(Course.NULL);
    }

    @Override
    public Course findCourseByShortTitle(String shortTitle) {
        return courses.stream().filter(c -> c.getShortTitle().matches("^.?*" +shortTitle+ ".?*$")).findFirst().orElse(Course.NULL);
    }

    @Override
    public void addCourse(Course c) {
        courses.add(new Course(
                c.getCourseId(),
                c.getShortTitle(),
                c.getLongTitle(),
                c.getNumberOfDays(),
                c.getPricePerDay()
        ));
    }

    @Override
    public void updateCourse(Course c) {
        if (c.getCourseId() != null) {
            Course c0 = courses.stream().filter(c1 -> Objects.equals(c1.getCourseId(), c.getCourseId())).findFirst().orElse(null);

        } else {

        }

    }

    @Override
    public void deleteCourse(int id) {

    }
}
