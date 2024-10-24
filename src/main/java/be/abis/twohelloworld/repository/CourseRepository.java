
package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Course;

import java.util.List;
import java.util.function.Predicate;

/**
 * generated source
 **/
interface CourseRepository {
    // Add an entity to the repository
    void add(Course ent);
    // Remove an entity from the repository
    void remove(Course ent);
    // Update an entity in the repository
    void update(Course ent);
    // Find entities using a lambda (predicate);
    List<Course> find(Predicate<? super Course> predicate);
    // Match entities using a regular expression on all fields (full-text search)
    List<Course> match(String regexpString);

    List<Course> all();
    void clear();
    int count();
}

