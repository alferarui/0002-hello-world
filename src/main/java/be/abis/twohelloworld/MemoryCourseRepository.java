package be.abis.twohelloworld;

import be.abis.twohelloworld.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemoryCourseRepository implements CourseRepository {
    ArrayList<Course> courses = new ArrayList<>(){{
        add(new Course(1,"Korean Kitchen","Basics of Korean Kitchen",10,100));
        add(new Course(2,"Assembly Language for Kindergarden","Accessible introduction to microcontrollers",10,100));
        add(new Course(3,"Around the World in 80 days","Meditation",10,100));
        add(new Course(4,"Chinese","introductory course to Chinese Writing",10,100));
        add(new Course(6,"Bricolage","the basics of household maintenance",10,100));
    }};

    @Override
    public List<Course> findAllCourses() {
        return courses;
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
            if(c0 != null){
                c0.setShortTitle(c.getShortTitle());
                c0.setLongTitle(c.getLongTitle());
                c0.setNumberOfDays(c.getNumberOfDays());
                c0.setPricePerDay(c.getPricePerDay());
            }
        }

    }

    @Override
    public void deleteCourse(int id) {
        courses.removeIf(c -> Objects.equals(c.getCourseId(), id));
    }
}