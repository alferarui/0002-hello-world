@Grab(group='com.github.javaparser', module='javaparser-core', version='3.25.4')

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.VariableDeclarator

def getClassInfo(String sourceFilePath) {
    def source = new File(sourceFilePath).text

    // Parse the Java source file
    def cu = StaticJavaParser.parse(source)

    // Get the class declaration from the source file
    def classDeclaration = cu.findFirst(ClassOrInterfaceDeclaration.class).get()

    def className = classDeclaration.nameAsString
    def idFields = []
    def regularFields = []

    // Iterate over all field declarations in the class
    classDeclaration.getFields().each { field ->
        field.getVariables().each { variable ->
            def fieldName = variable.nameAsString
            def isIdField = field.getAnnotations().any { it.nameAsString == 'MagicCsvId' }

            if (isIdField) {
                idFields.add(fieldName)
            } else {
                regularFields.add(fieldName)
            }
        }
    }

    // Return the class name, id fields, and regular fields
    return [
            className    : className,
            idFields     : idFields,
            regularFields: regularFields
    ]
}

// Function to list all available plugins
def listAvailablePlugins(File currentDir) {
    def pluginFiles = currentDir.listFiles({ file -> file.name.startsWith("generator.java.") && file.name.endsWith(".groovy") } as FileFilter)
    if (pluginFiles.size() > 0) {
        println "Available plugins:"
        pluginFiles.each { file ->
            println "- ${file.name.replace('generator.java.', '').replace('.groovy', '')}"
        }
    } else {
        println "No available plugins found."
    }
}

// Function to load and execute the corresponding repository generator plugin
def executeRepositoryGenerator(String pluginName, String className, List<String> idFields, List<String> fieldNames) {
    // Get the absolute path of the current directory (where this script is located)
    def currentDir = new File('.').absoluteFile
    print "using current dir $currentDir"
    // Construct the plugin file path based on the provided plugin name
    def pluginFilePath = new File(currentDir, "generator.java.${pluginName}.groovy")

    // Check if the plugin file exists
    def pluginFile = new File(pluginFilePath as String)
    if (!pluginFile.exists()) {
        println "Plugin file not found: ${pluginFilePath} in $currentDir"
        listAvailablePlugins(currentDir as File)  // List available plugins
        System.exit(1)
    }

    // Load the plugin file
    def scriptContent = pluginFile.text

    // Evaluate the script and call the generator function
    def script = new GroovyShell().evaluate(scriptContent)

    // Execute the repository template generator
    def repositoryTemplate = script.generate(className, idFields, fieldNames)
    println repositoryTemplate
}

// Main logic to process source file
if (args.length < 2) {
    println "Usage: groovy generator.groovy <plugin_name> <path/to/JavaFile.java>"
    System.exit(1)
}

def pluginName = args[0]  // The plugin (inmem or csvfile)
def sourceFilePath = args[1]  // The Java source file

// Extract the class info (class name, ID fields, regular fields)
def classInfo = getClassInfo(sourceFilePath)
def className = classInfo.className
def idFields = classInfo.idFields
def regularFields = classInfo.regularFields

// Execute the appropriate repository generator
executeRepositoryGenerator(pluginName, className as String, idFields, regularFields)