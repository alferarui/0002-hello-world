static String generate(String className, List<String> idFields, List<String> fieldNames) {
    return """
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

class CsvStorage${className}Repository {
    private List<${className}> storage = []
    private final String csvFilePath

    CsvStorage${className}Repository(String filePath) {
        this.csvFilePath = filePath
        loadFromFile()
    }

    // Add an entity to the repository
    void add(${className} ent) {
        if (!storage.find { matchesId(it, ent) }) {
            storage.add(ent)
            saveToFile()
        }
    }

    // Remove an entity from the repository
    void remove(${className} ent) {
        storage.removeIf { matchesId(it, ent) }
        saveToFile()
    }

    // Update an entity in the repository
    void update(${className} ent) {
        def existing = storage.find { matchesId(it, ent) }
        if (existing != null) {
            storage.remove(existing)
            storage.add(ent)
            saveToFile()
        }
    }

    // Find entities using a lambda (predicate)
    List<${className}> find(Closure<Boolean> predicate) {
        return storage.findAll { predicate.call(it) }
    }

    // Match entities using a regular expression on all fields (full-text search)
    List<${className}> match(String regexpString) {
        def pattern = regexpString.toPattern()
        return storage.findAll { ent ->
            ent.class.declaredFields.any { field ->
                field.accessible = true
                def value = field.get(ent)?.toString()
                value?.matches(pattern)
            }
        }
    }

    // Load entities from CSV file if it exists
    private void loadFromFile() {
        if (!Files.exists(Paths.get(csvFilePath))) return
        storage = loadFromCsv(csvFilePath)
    }

    // Save entities to the CSV file
    private void saveToFile() {
        saveToCsv(storage, csvFilePath)
    }

    // Helper method to match entities by ID fields
    private boolean matchesId(${className} entity1, ${className} entity2) {
        return ${idFields.collect { field -> "entity1.${field} == entity2.${field}" }.join(' && ')}
    }

    // Save list of entities to a CSV file
    private void saveToCsv(List<${className}> entities, String filePath) {
        def csvLines = entities.stream().map { ent ->
            [
                ${fieldNames.collect { field -> "ent.${field}" }.join(', ')}
            ].join(';')
        }.collect(Collectors.toList())

        Files.write(Paths.get(filePath), csvLines)
    }

    // Load entities from a CSV file
    private List<${className}> loadFromCsv(String filePath) {
        def lines = Files.readAllLines(Paths.get(filePath))
        return lines.collect { line ->
            def parts = line.split(';')
            new ${className}(
                ${fieldNames.collect { field -> "$field: parseField(parts[${fieldNames.indexOf(field)}], ${getTypeForField(className, field)})" }.join(',\n                    ')}
            )
        }
    }

    // Helper to parse CSV fields
    private static Object parseField(String value, Class fieldType) {
        if (fieldType == Long) return value ? value.toLong() : null
        if (fieldType == Integer) return value ? value.toInteger() : null
        if (fieldType == Boolean) return value ? value.toBoolean() : null
        return value
    }

    // Helper to get field type (replace this based on actual field types)
    private static Class getTypeForField(String className, String field) {
        switch (field) {
            ${fieldNames.collect { field -> "case '${field}': return ${className}.class.getDeclaredField('${field}').getType()" }.join('\n            ')}
        }
    }
}
"""
}
