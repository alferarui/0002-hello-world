import java.lang.reflect.Field

// Function to print class name and fields
def printClassInfo(Class clazz) {
    println "Class Name: ${clazz.simpleName}"

    Field[] fields = clazz.declaredFields

    fields.each { field ->
        def annotations = field.annotations
        def isIdField = annotations.find { it.annotationType().simpleName == 'MagicCsvId' } != null
        def fieldType = field.type.simpleName
        if (isIdField) {
            println "ID Field: ${field.name} (${fieldType})"
        } else {
            println "Field: ${field.name} (${fieldType})"
        }
    }
}

// Main logic to process class file
if (args.length < 2) {
    println "Usage: groovy script.groovy <package.name.ClassName> <path/to/class/files>"
    System.exit(1)
}

def className = args[0]
def classFilePath = args[1]

// Add Kotlin runtime to classpath
def kotlinStdlibLocation = "/home/alfu64/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.9.25/f700a2f2b8f0d6d0fde48f56d894dc722fb029d7/kotlin-stdlib-1.9.25.jar"
def kotlinStdlib = new File(kotlinStdlibLocation).toURI().toURL() // <-- Update with the correct path to the Kotlin stdlib
URL[] classUrls = [kotlinStdlib, new File(classFilePath).toURI().toURL()]
URLClassLoader classLoader = new URLClassLoader(classUrls)

// Load the class dynamically
Class clazz = classLoader.loadClass(className)

printClassInfo(clazz)
