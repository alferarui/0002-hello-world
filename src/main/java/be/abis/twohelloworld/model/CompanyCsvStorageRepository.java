

package be.abis.twohelloworld.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

class CompanyCsvStorageRepository implements CompanyRepository,SaverRepository {
    private final File fl = new File("CsvFileCourse.repository.csv");
    private final CompanyMemoryRepository memoryRepository = new CompanyMemoryRepository();
    private String csvFilePath = "CsvFileCourse.repository.csv";
    boolean memoryIsFresh=false;

    public CompanyCsvStorageRepository() {
        load();
    }

    public CompanyCsvStorageRepository(String filePath) {
        this.csvFilePath = filePath;
        load();
    }

    // Add an entity to the repository
    public void add(Company ent) {
        memoryRepository.add(ent);
    }

    // Remove an entity from the repository
    public void remove(Company ent) {
        memoryRepository.remove(ent);
        save();
    }

    // Update an entity in the repository
    public void update(Company ent) {
        memoryRepository.update(ent);
        save();
    }

    // Find entities using a lambda (predicate)
    public List<Company> find(Predicate<? super Company> predicate) {
        return memoryRepository.find(predicate);
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Company> match(String regexpString) {
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
                           new Company(){{
                               setVatNr(String.valueOf(cells[0]));
                               setCompanyId(Long.parseLong(cells[1]));
                               setName(String.valueOf(cells[2]));
                               setTelephoneNumber(String.valueOf(cells[3]));
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
                .map(company ->
                        (company.getVatNr()) + ";"
                            + Long.valueOf(company.getCompanyId()) + ";"
                            + (company.getName()) + ";"
                            + (company.getTelephoneNumber())
                )
                .toList();
        try {
            Files.write(Paths.get(csvFilePath), csvLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

