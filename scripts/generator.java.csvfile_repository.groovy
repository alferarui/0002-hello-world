
static def getDeserializer(String type){
    switch(type){
        case 'String' -> 'String.valueOf'
        case 'Date' -> 'Date.parse'
        case 'Integer' -> 'Integer.parseInt'
        case 'Double' -> 'Double.parseDouble'
        case 'LocalDate' -> 'LocalDate.parseDouble'
        case 'Long' -> 'Long.parseLong'
        default -> 'Object.valueOf'
    }
}

static LinkedHashMap<String, GString> generate(String packageName,String className, List<HashMap<String,String>> idFields, List<HashMap<String,String>> fieldNames) {
    def paramName=className.toLowerCase()
    def allFields = (idFields + fieldNames)
    GString source = """

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
                   memoryRepository.add(
                           new ${className}(){{
                               ${allFields
                                    .stream()
                                    .map { field ->
                                        println field
                                        println field.name
                                        println field.type
                                        println('===================')
                                        def fieldIndex = allFields.indexOf(field)
                                        def fieldDeserializer = getDeserializer(field.type)
                                        "set${field.name.capitalize()}(${fieldDeserializer}(cells[$fieldIndex]));"
                                    }.toList().join('\n                               ')
                               }
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
                        ${allFields
                            .stream()
                            .map { field ->
                                "${paramName}.get${field.name.capitalize()}()"
                            }.toList().join(' + ";"\n                            + ')
                        }
                )
                .toList();
        try {
            Files.write(Paths.get(csvFilePath), csvLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

"""
    GString filename = "CsvStorage${className}Repository.java"
    return [
            filename : filename,
            source: source
    ]
}

// Return the function so it can be used after the script is loaded
return this.&generate