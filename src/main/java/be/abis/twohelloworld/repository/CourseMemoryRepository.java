
package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * @apiNote generated using generator.java.class_inmem_repository.groovy generator
 *          Target entity be.abis.twohelloworld.model.Course
 *          Entity Fields 
 *           - (Id) shortTitle
 *           - NULL
 *           - courseId
 *           - longTitle
 *           - numberOfDays
 *           - pricePerDay
 */
class CourseMemoryRepository implements CourseRepository{
    private final ArrayList<Course> storage = new ArrayList<>();
    public List<Course> all(){
        return storage;
    }
    public CourseMemoryRepository(){}

    // Add an entity to the repository
    public void add(Course ent) {
        if (storage.stream().noneMatch(e -> matchesId(e, ent))) {
            storage.add(ent);
        }
    }

    // Remove an entity from the repository
    public void remove(Course ent) {
        storage.removeIf ( e -> matchesId(e, ent) );
    }

    // Update an entity in the repository
    public void update(Course ent) {
        var exists = storage.stream().anyMatch(e -> matchesId(e, ent));
        if(exists) {
            storage.removeIf(course -> matchesId(course, ent));
            storage.add(ent);
        }
    }

    // Find entities using a lambda (predicate)
    public List<Course> find(Predicate<? super Course> predicate) {
        return storage.stream().filter ( predicate ).toList();
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Course> match(String regexpString) {
        return storage.stream().filter(course -> stringify(course).matches(regexpString)).toList();
    }

    public void clear(){
        storage.clear();
    }

    private String stringify(Course course) {
        return (course.getShortTitle())+ "$" /*+(course.getNULL())*/+ "$" +(course.getCourseId())+ "$" +(course.getLongTitle())+ "$" +(course.getNumberOfDays())+ "$" +(course.getPricePerDay());
    }
    private String stringifyId(Course course) {
        return (course.getShortTitle());
    }

    // Helper method to match entities by ID fields
    private boolean matchesId(Course course1, Course course2) {
        return stringifyId(course1).equals(stringifyId(course2));
    }
}
