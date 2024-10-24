

package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Address;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

class AddressCsvStorageRepository implements AddressRepository, SaverRepository {
    private final File fl = new File("CsvFileCourse.repository.csv");
    private final AddressMemoryRepository memoryRepository = new AddressMemoryRepository();
    private String csvFilePath = "CsvFileCourse.repository.csv";
    boolean memoryIsFresh=false;

    public AddressCsvStorageRepository() {
        load();
    }

    public AddressCsvStorageRepository(String filePath) {
        this.csvFilePath = filePath;
        load();
    }

    // Add an entity to the repository
    public void add(Address ent) {
        memoryRepository.add(ent);
    }

    // Remove an entity from the repository
    public void remove(Address ent) {
        memoryRepository.remove(ent);
        save();
    }

    // Update an entity in the repository
    public void update(Address ent) {
        memoryRepository.update(ent);
        save();
    }

    // Find entities using a lambda (predicate)
    public List<Address> find(Predicate<? super Address> predicate) {
        return memoryRepository.find(predicate);
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<Address> match(String regexpString) {
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
                           new Address(){{
                               setStreet(String.valueOf(cells[0]));
                               setNr(String.valueOf(cells[1]));
                               setZipCode(String.valueOf(cells[2]));
                               setAddressId(Long.parseLong(cells[3]));
                               setTown(String.valueOf(cells[4]));
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
                .map(address ->
                        (address.getStreet()) + ";"
                            + (address.getNr()) + ";"
                            + (address.getZipCode()) + ";"
                            + (address.getAddressId()) + ";"
                            + (address.getTown())
                )
                .toList();
        try {
            Files.write(Paths.get(csvFilePath), csvLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

