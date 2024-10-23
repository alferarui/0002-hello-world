@Grab(group='com.github.javaparser', module='javaparser-core', version='3.25.4')

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.VariableDeclarator

def getClassInfo(String sourceFilePath) {
    def source = new File(sourceFilePath).text

    // Parse the Java source file
    def cu = StaticJavaParser.parse(source)

    // Get the package declaration if present
    def packageDeclaration = cu.getPackageDeclaration().map { it.nameAsString }.orElse("")
    print(packageDeclaration)

    // Get the class declaration from the source file
    ClassOrInterfaceDeclaration classDeclaration = cu.findFirst(ClassOrInterfaceDeclaration.class).get()

    def className = classDeclaration.nameAsString
    def idFields = []
    def regularFields = []
    def types = []

    // Iterate over all field declarations in the class
    classDeclaration.getFields().each { field ->
        field.getVariables().each { variable ->
            def fieldName = variable.nameAsString
            def isIdField = field.getAnnotations().any { it.nameAsString == 'MagicCsvId' }

            if (isIdField) {
                idFields.add([name:fieldName,type:variable.typeAsString])
            } else {
                regularFields.add([name:fieldName,type:variable.typeAsString])
            }
        }
    }

    // Return the class name, id fields, and regular fields
    return [
            packageName: packageDeclaration.toString(),
            className    : className,
            idFields     : idFields,
            regularFields: regularFields,
            types: types
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
LinkedHashMap<String, GString> executeRepositoryGenerator(String pluginName, String packageName, String className, List<HashMap<String,String>> idFields, List<HashMap<String,String>> fieldNames) {
    // Get the absolute path of the current directory (where this script is located)
    def currentDir = new File('scripts').absoluteFile
    //println "using current dir $currentDir"
    // Construct the plugin file path based on the provided plugin name
    def pluginFilePath = new File(currentDir, "generator.java.${pluginName}.groovy").canonicalFile
    //println "loading $pluginFilePath"

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

    //println script
    // Execute the repository template generator
    LinkedHashMap<String, GString> repositoryTemplate = script.generate(packageName,className, idFields, fieldNames)
    return repositoryTemplate
}
// Function to save the generated template to a file
def saveToFile(String content, String outputPath) {
    try {
        // Write the content to the file
        def outputFile = new File(outputPath)
        outputFile.withWriter { writer ->
            writer.write(content)
        }
        println "Generated repository saved to: ${outputFile.absolutePath}"
    } catch (Exception e) {
        println "Error writing to file: ${e.message}"
    }
}
// Main logic to process source file
if (args.length < 2) {
    println "Usage: groovy generator.groovy <plugin_name> <path/to/JavaFile.java>"
    System.exit(1)
}

def pluginName = args[0]  // The plugin (inmem or csvfile)
def sourceFilePath = args[1]  // The Java source file
println "pluginName : $pluginName"
println "sourceFilePath : $sourceFilePath"
// Extract the class info (class name, ID fields, regular fields)
def classInfo = getClassInfo(sourceFilePath)
def packageName = classInfo.packageName
def className = classInfo.className
def idFields = classInfo.idFields
def regularFields = classInfo.regularFields

// Execute the appropriate repository generator
def generatorResult = executeRepositoryGenerator(pluginName, packageName, className as String, idFields, regularFields)

String outPath = "${new File(sourceFilePath).parentFile.toString()}/${generatorResult.filename}"

saveToFile(generatorResult.source as String, outPath)