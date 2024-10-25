package be.abis.twohelloworld.service;

import be.abis.twohelloworld.model.Course;
import be.abis.twohelloworld.repository.CourseCsvStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceDisk implements CourseService{

    @Autowired
    CourseCsvStorageRepository storageRepository;

    @Override
    public List<Course> findAllCourses() {
        return storageRepository.all();
    }

    @Override
    public Course findCourseById(int id) {
        return storageRepository.find(c -> c.getCourseId() == id).get(0);
    }

    @Override
    public Course findCourseByShortTitle(String shortTitle) {
        return storageRepository.find(c -> c.getShortTitle().equals(shortTitle)).get(0);
    }

    @Override
    public void addCourse(Course c) {
        storageRepository.add(c);
    }

    @Override
    public void updateCourse(Course c) {
        storageRepository.update(c);
    }

    @Override
    public void deleteCourse(int id) {
        Course course = storageRepository.find(c -> c.getCourseId() == id).get(0);
        storageRepository.remove(course);
    }
}
