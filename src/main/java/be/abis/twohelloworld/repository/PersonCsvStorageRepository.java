

package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Person;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

@Repository
class PersonCsvStorageRepository implements PersonRepository,SaverRepository {
    private final File fl = new File("Person.repository.csv");
    private final PersonMemoryRepository memoryRepository = new PersonMemoryRepository();
    private String csvFilePath = "Person.repository.csv";
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

    @Override
    public int count() {
        return memoryRepository.count();
    }

    @Override
    public void clear() {
        memoryRepository.clear();
        save();
    }

    @Override
    public List<Person> all() {
        return memoryRepository.all();
    }

    public void load(){
        if(fl.exists() && !memoryIsFresh) {
            // read all
            try{
                memoryRepository.clear();
                final List<String> lines = Files.readAllLines(Paths.get(csvFilePath));
                for(String line:lines){
                    System.out.println(line);
                    var cells = line.split(";");
                    try{
                        memoryRepository.add(
                                new Person(){{
                                    setPersonId(Integer.parseInt(cells[0]));
                                    setFirstName(String.valueOf(cells[1]));
                                    setLastName(String.valueOf(cells[2]));
                                    setBirthday(LocalDate.parse(cells[3]));
                                    setEmailAddress(String.valueOf(cells[4]));
                                    setHomeAddress(String.valueOf(cells[5]));
                                    setLanguage(String.valueOf(cells[6]));
                                    setPhone(String.valueOf(cells[7]));
                                    setMobile(String.valueOf(cells[8]));
                                    setStreet(String.valueOf(cells[9]));
                                    setNumber(String.valueOf(cells[10]));
                                    setZipCode(String.valueOf(cells[11]));
                                    setCity(String.valueOf(cells[12]));
                                }}
                        );
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
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
                        (person.getEmailAddress()) + ";"
                            + (person.getPersonId()) + ";"
                            + (person.getFirstName()) + ";"
                            + (person.getLastName()) + ";"
                            + (person.getBirthday()) + ";"
                            + (person.getHomeAddress()) + ";"
                            + (person.getLanguage()) + ";"
                            + (person.getPhone()) + ";"
                            + (person.getMobile()) + ";"
                            + (person.getStreet()) + ";"
                            + (person.getNumber()) + ";"
                            + (person.getZipCode()) + ";"
                            + (person.getCity())
                )
                .toList();
        try {
            Files.write(Paths.get(csvFilePath), csvLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

