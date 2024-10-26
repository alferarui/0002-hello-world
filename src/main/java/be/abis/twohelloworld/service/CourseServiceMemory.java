package be.abis.twohelloworld.service;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.repository.memory.CourseMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceMemory implements CourseService{

    @Autowired
    CourseMemoryRepository memoryRepository;

    @Override
    public List<Course> findAllCourses() {
        return memoryRepository.all();
    }

    @Override
    public Course findCourseById(int id) {
        return memoryRepository.find(c -> c.getCourseId() == id).get(0);
    }

    @Override
    public Course findCourseByShortTitle(String shortTitle) {
        return memoryRepository.find(c -> c.getShortTitle().equals(shortTitle)).get(0);
    }

    @Override
    public void addCourse(Course c) {
        memoryRepository.add(c);
    }

    @Override
    public void updateCourse(Course c) {
        memoryRepository.update(c);
    }

    @Override
    public void deleteCourse(int id) {
        Course course = memoryRepository.find(c -> c.getCourseId() == id).get(0);
        memoryRepository.remove(course);
    }
}
