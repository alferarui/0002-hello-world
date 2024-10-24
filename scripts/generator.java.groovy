@Grab(group='com.github.javaparser', module='javaparser-core', version='3.25.4')

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
class PackageInfo{

}
class FieldInfo{}

static def getDeserializer(String type){
    switch(type){
        case 'String' -> 'String.valueOf'
        case 'Integer' -> 'Integer.parseInt'
        case 'Long' -> 'Long.parseLong'
        case 'Double' -> 'Double.parseDouble'
        case 'Date' -> 'Date.parse'
        case 'LocalDate' -> 'LocalDate.parse'
        default -> "/*deserializer not found ${type}*/" as String
    }
}

def getClassInfo(String sourceFilePath) {
    def source = new File(sourceFilePath).text

    // Parse the Java source file
    def cu = StaticJavaParser.parse(source)

    // Get the package declaration if present
    def packageDeclaration = cu.getPackageDeclaration().map { it.nameAsString }.orElse("")
    def parentPackage = packageDeclaration.toString().split('\\.')[0..-2].join(".")
    println(packageDeclaration)
    println(parentPackage)

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
            def ignore = field.getAnnotations().any { it.nameAsString == 'MagicCsvIgnore' }
            if(ignore){
                println("ignoring field $fieldName")
                return
            }
            def isIdField = field.getAnnotations().any { it.nameAsString == 'MagicCsvId' }
            def deserializer = "/*default deserializer ${variable.typeAsString}*/"
            def serializer = "/*default serializer ${variable.typeAsString}*/Objects.toString"
            // Check if field is annotated with @MagicCsvField and retrieve its values
            def magicCsvFieldAnnotation = field.getAnnotations().find { it.nameAsString == 'MagicCsvField' }
            if (magicCsvFieldAnnotation) {
                println("custom -/deserializers field $fieldName")
                magicCsvFieldAnnotation.getPairs().each {
                    println(" **** ${it.getName()} ${it.getValue()} ")
                    switch(it.getName()){
                        case 'deserializer' -> deserializer = (it.getValue() as String).replace("\"","")
                        case 'serializer' -> serializer = (it.getValue() as String).replace("\"","")
                    }
                }
                println(" - deserializer : $deserializer")
                println(" - serializer : $serializer")
            } else {
                deserializer=getDeserializer(variable.typeAsString as String)
                serializer=''
            }
            if (isIdField) {
                idFields.add([name:fieldName,type:variable.typeAsString,deserializer:deserializer,serializer:serializer])
            } else {
                regularFields.add([name:fieldName,type:variable.typeAsString,deserializer:deserializer,serializer:serializer])
            }
        }
    }

    // Return the class name, id fields, and regular fields
    return [
            parentPackageName: "${parentPackage}.repo",
            packageName: packageDeclaration,
            className    : className,
            idFields     : idFields,
            regularFields: regularFields,
            types: types
    ]
}

static List<File> availablePlugins(File pluginsDir){
    def files=[]
    pluginsDir
            .listFiles({ file -> file.name.startsWith("generator.plugin.") && file.name.endsWith(".groovy") } as FileFilter)
            .each { f -> files.add(f)}
    return files
}
// Function to list all available plugins
def listAvailablePlugins(File pluginsDir) {
    def pluginFiles = availablePlugins(pluginsDir)
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
List<LinkedHashMap<String, GString>> executeGenerator(String pluginName, String packageName, String className, List<HashMap<String,String>> idFields, List<HashMap<String,String>> fieldNames) {
    // Get the absolute path of the current directory (where this script is located)
    def currentDir = new File('scripts').absoluteFile
    //println "using current dir $currentDir"
    // Construct the plugin file path based on the provided plugin name
    def pluginFilePath = new File(currentDir, "generator.java.${pluginName}.groovy").canonicalFile
    //println "loading $pluginFilePath"

    // Check if the plugin file exists
    File pluginFile = new File(pluginFilePath as String)
    if (!pluginFile.exists()) {
        if(pluginName != '--all') {
            println "Plugin file not found: ${pluginFilePath} in $currentDir"
            listAvailablePlugins(currentDir as File)  // List available plugins
            System.exit(1)
        } else {
            println "generating all in $currentDir"
            ArrayList<LinkedHashMap<String, GString>> all = []
            availablePlugins(currentDir as File)
                .each{ pluginFile0 ->
                    println " === generating $pluginFile0 -------------------------------------------"
                    // Load the plugin file
                    def scriptContent0 = pluginFile0.text

                    // Evaluate the script and call the generator function
                    def script = new GroovyShell().evaluate(scriptContent0)

                    //println script
                    // Execute the repository template generator
                    List<LinkedHashMap<String, GString>> generatedFiles0 = script.generate(packageName,className, idFields, fieldNames)
                    generatedFiles0.each{all.add(it) }
                }
            return all
        }
    } else {

        // Load the plugin file
        def scriptContent = pluginFile.text

        // Evaluate the script and call the generator function
        def script = new GroovyShell().evaluate(scriptContent)

        //println script
        // Execute the repository template generator
        List<LinkedHashMap<String, GString>> generatedFiles = script.generate(packageName,className, idFields, fieldNames)
        return generatedFiles

    }
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

List<LinkedHashMap<String, String>>  generate(String pluginName,String sourceFilePath){

    // Extract the class info (class name, ID fields, regular fields)
    def classInfo = getClassInfo(sourceFilePath)
    def packageName = classInfo.packageName
    def className = classInfo.className
    def idFields = classInfo.idFields
    def regularFields = classInfo.regularFields

    // Execute the appropriate repository generator
    def generatorResult = executeGenerator(pluginName, packageName, className as String, idFields, regularFields)
    String parentFolder = new File(sourceFilePath).parentFile.toString()
    return generatorResult.stream().map { it ->

        String outPath = "${parentFolder}/${it.filename}"
        [
            packageName:packageName as String,
            className:className as String,
            generatedSource: it.source as String,
            filename:it.filename as String,
            fullFileName: outPath as String
        ]
    }.toList()
}

def main() {
// Main logic to process source file
    if (args.length < 2) {
        println "Usage: groovy generator.groovy <plugin_name> <path/to/JavaFile.java>"
        System.exit(1)
    }
    def pluginName = args[0]  // The plugin (inmem or csvfile)
    def sourceFilePath = args[1]  // The Java source file
    println "pluginName : $pluginName"
    println "sourceFilePath : $sourceFilePath"

    def generatorResults = generate(pluginName, sourceFilePath)

    for(LinkedHashMap<String, String> generatorResult in generatorResults) {
        println "saving to ${generatorResult.fullFileName}"
        saveToFile(generatorResult.generatedSource, generatorResult.fullFileName)
    }
}

main()
return this.&generate