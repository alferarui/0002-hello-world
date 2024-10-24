package be.abis.twohelloworld.service;


import be.abis.twohelloworld.repository.CourseMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseMemoryRepository courseRepository;
}
