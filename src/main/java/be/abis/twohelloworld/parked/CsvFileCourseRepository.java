package be.abis.twohelloworld.parked;

import be.abis.twohelloworld.model.Course;
import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvFileCourseRepository implements CourseRepository{
    private final File fl = new File("CsvFileCourse.repository.csv");
    private MemoryCourseRepository memoryCourseRepository = new MemoryCourseRepository();
    private boolean memoryIsFresh=false;

    public void load(){
        if(fl.exists() && !memoryIsFresh) {
            // read all
            try(final FileInputStream fis = new FileInputStream(fl)) {
                final byte[] bytes = fis.readAllBytes();
                final String csv = new String(bytes, StandardCharsets.UTF_8);
                memoryCourseRepository = new MemoryCourseRepository();
                memoryCourseRepository.courses.clear();
                memoryCourseRepository.initFromCsv(csv);
                memoryIsFresh=true;
            } catch (IOException e) {
                System.out.println("error reading file");
                throw new RuntimeException(e);
            }
        }
    }

    public void save(){
        if(fl.exists()) {
            fl.delete();
        }

        try(final FileOutputStream fos = new FileOutputStream(fl)) {
            final List<String> lines = memoryCourseRepository.courses
                    .stream()
                    .map(course ->
                            course.getCourseId()+";" +
                                    course.getShortTitle()+";" +
                                    course.getLongTitle()+";" +
                                    course.getNumberOfDays()+";" +
                                    course.getPricePerDay()+";"
                    )
                    .toList();
            fos.write(Strings.join(lines,'\n').getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> findAllCourses() {
        load();
        return memoryCourseRepository.courses;
    }

    @Override
    public Course findCourseById(int id) {
        load();
        return memoryCourseRepository.findCourseById(id);
    }

    @Override
    public Course findCourseByShortTitle(String shortTitle) {
        load();
        return memoryCourseRepository.findCourseByShortTitle(shortTitle);
    }

    @Override
    public List<Course> findCoursesByDuration(int duration) {
        return memoryCourseRepository.findCoursesByDuration(duration);
    }

    @Override
    public void addCourse(Course c) {
        load();
        memoryCourseRepository.addCourse(c);
        save();
    }

    @Override
    public void updateCourse(Course c) {
        load();
        memoryCourseRepository.updateCourse(c);
        save();
    }

    @Override
    public void deleteCourse(int id) {
        load();
        memoryCourseRepository.deleteCourse(id);
        save();
    }
}
