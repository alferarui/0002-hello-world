


static List<LinkedHashMap<String, GString>> generate(String packageName,String className, List<HashMap<String,String>> idFields, List<HashMap<String,String>> fieldNames) {
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

class ${className}CsvStorageRepository implements ${className}Repository,SaverRepository {
    private final File fl = new File("${className}.repository.csv");
    private final ${className}MemoryRepository memoryRepository = new ${className}MemoryRepository();
    private String csvFilePath = "${className}.repository.csv";
    boolean memoryIsFresh=false;

    public ${className}CsvStorageRepository() {
        load();
    }

    public ${className}CsvStorageRepository(String filePath) {
        this.csvFilePath = filePath;
        load();
    }

    // Add an entity to the repository
    public void add(${className} ent) {
        memoryRepository.add(ent);
    }

    // Remove an entity from the repository
    public void remove(${className} ent) {
        memoryRepository.remove(ent);
        save();
    }

    // Update an entity in the repository
    public void update(${className} ent) {
        memoryRepository.update(ent);
        save();
    }

    // Find entities using a lambda (predicate)
    public List<${className}> find(Predicate<? super ${className}> predicate) {
        return memoryRepository.find(predicate);
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<${className}> match(String regexpString) {
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
    public List<${className}> all() {
        return memoryRepository.all();
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
                                        def fieldDeserializer = field.deserializer
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
                                "${field.serializer}(${paramName}.get${field.name.capitalize()}())"
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
    GString filename = "${className}CsvStorageRepository.java"

    ArrayList<LinkedHashMap<String, GString>> list = new ArrayList<LinkedHashMap<String, GString>>()
    list.add([
            filename : filename,
            source: source
    ])
    return list
}

// Return the function so it can be used after the script is loaded
return this.&generate