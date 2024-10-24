

package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Course;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

class CourseCsvStorageRepository implements CourseRepository, SaverRepository {
    private final File fl = new File("CsvFileCourse.repository.csv");
    private final CourseMemoryRepository memoryRepository = new CourseMemoryRepository();
    private String csvFilePath = "CsvFileCourse.repository.csv";
    boolean memoryIsFresh=false;

    public CourseCsvStorageRepository() {
        load();
    }

    public CourseCsvStorageRepository(String filePath) {
        this.csvFilePath = filePath;
        load();
    }

    // Add an entity to the repository
    public void add(Course ent) {
        memoryRepository.add(ent);
    }

    // Remove an entity from the repository
    public void remove(Course ent) {
        memoryRepository.remove(ent);
        save();
    }

    // Update an entity in the repository
    public void update(Course ent) {
        memoryRepository.update(ent);
        save();
    }

    // Find entities using a lambda (predicate)
    public List<Course> find(Predicate<? super Course> predicate) {
        return memoryRepository.find(predicate);
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Course> match(String regexpString) {
        return memoryRepository.match(regexpString);
    }

    public void load(){
        if(fl.exists() && !memoryIsFresh) {
            // read all
            try{
                memoryRepository.clear();
                final List<String> lines = Files.readAllLines(Paths.get(csvFilePath));
                for(String line:lines){
                    var cells = line.split(";");
                   memoryRepository.add(
                           new Course(){{
                               setShortTitle(String.valueOf(cells[0]));
                               //setNULL(/*deserializer not found Course*/(cells[1]));
                               setCourseId(Integer.parseInt(cells[2]));
                               setLongTitle(String.valueOf(cells[3]));
                               setNumberOfDays(Integer.parseInt(cells[4]));
                               setPricePerDay(Integer.parseInt(cells[5]));
                           }}
                   );
                }
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

        final List<String> csvLines = memoryRepository.all()
                .stream()
                .map(course ->
                        (course.getShortTitle()) + ";"
                            //+ (course.getNULL()) + ";"
                            + (course.getCourseId()) + ";"
                            + (course.getLongTitle()) + ";"
                            + (course.getNumberOfDays()) + ";"
                            + (course.getPricePerDay())
                )
                .toList();
        try {
            Files.write(Paths.get(csvFilePath), csvLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
