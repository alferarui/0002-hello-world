

package be.abis.twohelloworld.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

class PersonCsvStorageRepository implements PersonRepository,SaverRepository {
    private final File fl = new File("CsvFileCourse.repository.csv");
    private final PersonMemoryRepository memoryRepository = new PersonMemoryRepository();
    private String csvFilePath = "CsvFileCourse.repository.csv";
    boolean memoryIsFresh=false;

    public PersonCsvStorageRepository() {
        load();
    }

    public PersonCsvStorageRepository(String filePath) {
        this.csvFilePath = filePath;
        load();
    }

    // Add an entity to the repository
    public void add(Person ent) {
        memoryRepository.add(ent);
    }

    // Remove an entity from the repository
    public void remove(Person ent) {
        memoryRepository.remove(ent);
        save();
    }

    // Update an entity in the repository
    public void update(Person ent) {
        memoryRepository.update(ent);
        save();
    }

    // Find entities using a lambda (predicate)
    public List<Person> find(Predicate<? super Person> predicate) {
        return memoryRepository.find(predicate);
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Person> match(String regexpString) {
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
                           new Person(){{
                               setFirstName(String.valueOf(cells[0]));
                               setPersonId(Integer.parseInt(cells[1]));
                               setLastName(String.valueOf(cells[2]));
                               setBirthday(LocalDate.parse(cells[3]));
                               setEmailAddress(String.valueOf(cells[4]));
                               setHomeAddress(String.valueOf(cells[5]));
                               setPassword(String.valueOf(cells[6]));
                               setLanguage(String.valueOf(cells[7]));
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
                .map(person ->
                        (person.getFirstName()) + ";"
                            + (person.getPersonId()) + ";"
                            + (person.getLastName()) + ";"
                            + (person.getBirthday()) + ";"
                            + (person.getEmailAddress()) + ";"
                            + (person.getHomeAddress()) + ";"
                            + (person.getPassword()) + ";"
                            + (person.getLanguage())
                )
                .toList();
        try {
            Files.write(Paths.get(csvFilePath), csvLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

