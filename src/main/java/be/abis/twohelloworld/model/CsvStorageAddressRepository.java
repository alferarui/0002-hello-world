
package ${packageName};

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

class CsvStorage${className}Repository {
    private final File fl = new File("CsvFileCourse.repository.csv");
    private final InMemory${className}Repository memoryRepository = new InMemory${className}Repository();
    private String csvFilePath = "CsvFileCourse.repository.csv";
    boolean memoryIsFresh=false;

    public CsvStorage${className}Repository() {
        load();
    }

    public CsvStorage${className}Repository(String filePath) {
        this.csvFilePath = filePath;
        load();
    }

    // Add an entity to the repository
    void add(${className} ent) {
        memoryRepository.add(ent);
    }

    // Remove an entity from the repository
    void remove(${className} ent) {
        memoryRepository.remove(ent);
        save();
    }

    // Update an entity in the repository
    void update(${className} ent) {
        memoryRepository.update(ent);
        save();
    }

    // Find entities using a lambda (predicate)
    List<${className}> find(Predicate<? super ${className}> predicate) {
        return memoryRepository.find(predicate);
    }

    // Match entities using a regular expression on all fields (full-text search)
    List<${className}> match(String regexpString) {
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
                /*

                    new ${className}(){{
                        ${(idFields+fieldNames).stream().map{ field ->
                            "set${field.capitalize()}(${deserializer}parts[${fieldNames.indexOf(field)}]);"
                        }.toList().join('\n                ')
                    }}
                 */
                    memoryRepository.add(
                            new ${className}(){{
                                set${className}Id(Long.parseLong(cells[0]));
                                setStreet(String.valueOf(cells[1]));
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
                .map(${paramName} ->
                        ${paramName}.getStreet()+";" +
                        ${paramName}.getNr()+";" +
                        ${paramName}.getZipCode()+";" +
                        ${paramName}.get${className}Id()+";" +
                        ${paramName}.getTown()
                )
                .toList();
        try {
            Files.write(Paths.get(csvFilePath), csvLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
