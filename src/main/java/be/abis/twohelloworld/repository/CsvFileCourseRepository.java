package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Course;
import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvFileCourseRepository implements CourseRepository{
    private final File fl = new File("CsvFileCourse.repository.csv");
    private MemoryCourseRepository memoryCourseRepository = new MemoryCourseRepository();;
    int writes=0;
    int reads=0;

    public void load(){
        if(fl.exists()) {
            // read all
            try(final FileInputStream fis = new FileInputStream(fl)) {
                final byte[] bytes = fis.readAllBytes();
                final String csv = new String(bytes, StandardCharsets.UTF_8);
                memoryCourseRepository = new MemoryCourseRepository();
                memoryCourseRepository.courses.clear();
                memoryCourseRepository.initFromCsv(csv);
                reads+=1;
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
            writes+=1;
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
