static String  generate(String className, List<String> idFields, List<String> fieldNames) {
    return """
class InMemory${className}Repository {
    private ArrayList<${className}> storage = []

    // Add an entity to the repository
    void add(${className} ent) {
        if (!storage.find { matchesId(it, ent) }) {
            storage.add(ent)
        }
    }

    // Remove an entity from the repository
    void remove(${className} ent) {
        storage.removeIf { matchesId(it, ent) }
    }

    // Update an entity in the repository
    void update(${className} ent) {
        def existing = storage.find { matchesId(it, ent) }
        if (existing != null) {
            storage.remove(existing)
            storage.add(ent)
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

    // Helper method to match entities by ID fields
    private boolean matchesId(${className} entity1, ${className} entity2) {
        return ${idFields.collect { field -> "entity1.${field} == entity2.${field}" }.join(' && ')}
    }
}
"""
}
