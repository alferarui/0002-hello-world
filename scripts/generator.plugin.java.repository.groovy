
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

static List<LinkedHashMap<String, GString>> generate(String packageName,String className, List<HashMap<String,String>> idFields, List<HashMap<String,String>> fieldNames) {
    def paramName=className.toLowerCase()
    def allFields = (idFields + fieldNames)
    GString sourceInterfaceRepository = """
package ${packageName};

import java.util.List;
import java.util.function.Predicate;

/**
 * generated source
 **/
interface ${className}Repository {
    // Add an entity to the repository
    void add(${className} ent);
    // Remove an entity from the repository
    void remove(${className} ent);
    // Update an entity in the repository
    void update(${className} ent);
    // Find entities using a lambda (predicate);
    List<${className}> find(Predicate<? super ${className}> predicate);
    // Match entities using a regular expression on all fields (full-text search)
    List<${className}> match(String regexpString);

    int count();
    void clear();
    List<${className}>  all();
}

"""
    GString filenameInterfaceRepository = "${className}Repository.java" as GString
    GString filenameSaver = "SaverRepository.${"java"}" as GString
    GString sourceSaver = """
package ${packageName};

interface SaverRepository {
    public void load();
    public void save();
}

"""
    return [[
                    filename : filenameInterfaceRepository,
                    source: sourceInterfaceRepository
            ],[
            filename : filenameSaver,
            source: sourceSaver
    ]
    ]
}

// Return the function so it can be used after the script is loaded
return this.&generate