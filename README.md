# Hello World - Project 0002

This repository contains a simple "Hello World" project setup using Java and Gradle.

## Project Structure

- **src/main**: Contains the main Java source files.
- **src/test**: Contains test files.
- **gradle/wrapper**: Contains the Gradle wrapper for build automation.
- **scripts/**: Contains Groovy scripts for model generation.

## Groovy Generators

The Groovy scripts in the `scripts` folder are used for generating code models automatically. These scripts simplify the process of creating repetitive or template-based code structures within the project.

the main script is [generator.java.groovy](scripts/generator.java.groovy)

it accepts two parameters :
1 - the generator plugin that will be used for source code generation
2 - the relative path of a POJO that describes the entity that will generate source code.

when using inmem_repository and/or csv_repository the POJO fields should be annotated with @MagicCsv* to convey the info oabout the de/-serializers, the fields participating at the unique key or whether the field should be ignored
if these annotations are not present the generated code could be faulty ( since there's no primary key ) ... so add @MagiCsvId to at least one of the POJO's fields.

the files will be generated in the POJO's package, you can move them around in intellij or vscode with ease ( it'll count as refactoring )
examples
```bash
groovy \
  scripts/generator.java.groovy \
     --all \
     src/main/java/be/abis/twohelloworld/model/Person.java
     

groovy \
  scripts/generator.java.groovy \
     class_inmem_repository \
     src/main/java/be/abis/twohelloworld/model/Person.java
    
groovy \
  scripts/generator.java.groovy \
     repository \
     src/main/java/be/abis/twohelloworld/model/Person.java
    
```




## Prerequisites

- Java 8+
- Gradle

## Build and Run

To build and run the project, use the following commands:

```bash
./gradlew build
./gradlew run
