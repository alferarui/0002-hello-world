static LinkedHashMap<String, GString>  generate(String packageName,String className, List<String> idFields, List<String> fieldNames) {
    def paramName=className.toLowerCase()
    GString source = """
package ${packageName};

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * @apiNote generated using generator.java.class_inmem_repository.groovy generator
 *          Target entity ${packageName}.${className}
 *          Entity Fields ${([""] + idFields).join('\n *           - (Id) ')}${([""] + fieldNames).join('\n *           - ')}
 */
class InMemory${className}Repository {
    private final ArrayList<${className}> storage = new ArrayList<>();
    public List<${className}> all(){
        return storage;
    }

    // Add an entity to the repository
    public void add(${className} ent) {
        if (storage.stream().noneMatch(e -> matchesId(e, ent))) {
            storage.add(ent);
        }
    }

    // Remove an entity from the repository
    public void remove(${className} ent) {
        storage.removeIf ( e -> matchesId(e, ent) );
    }

    // Update an entity in the repository
    public void update(${className} ent) {
        var exists = storage.stream().anyMatch(e -> matchesId(e, ent));
        if(exists) {
            storage.removeIf(${paramName} -> matchesId(${paramName}, ent));
            storage.add(ent);
        }
    }

    // Find entities using a lambda (predicate)
    public List<${className}> find(Predicate<? super ${className}> predicate) {
        return storage.stream().filter ( predicate ).toList();
    }

    // Match entities using a regular expression on all fields (full-text search)
    public List<${className}> match(String regexpString) {
        return storage.stream().filter(${paramName} -> stringify${className}(${paramName}).matches(regexpString)).toList();
    }

    public void clear(){
        storage.clear();
    }

    private String stringify${className}(${className} ${paramName}) {
        return ${
        (idFields + fieldNames).stream().map { fieldName -> """${paramName}.get${fieldName.capitalize()}()""" }.toList().join('+ "\$" +')
        };
    }
    private String stringify${className}Id(${className} ${paramName}) {
        return ${
            idFields.stream().map { fieldName -> """${paramName}.get${fieldName.capitalize()}()""" }.toList().join('+ "$" +')
        };
    }

    // Helper method to match entities by ID fields
    private boolean matchesId(${className} ${paramName}1, ${className} ${paramName}2) {
        return stringify${className}Id(${paramName}1).equals(stringify${className}Id(${paramName}2));
    }
}
"""
    GString filename="InMemory${className}Repository.java"
    return [
            filename : filename,
            source : source
    ]
}


// Return the function so it can be used after the script is loaded
return this.&generate