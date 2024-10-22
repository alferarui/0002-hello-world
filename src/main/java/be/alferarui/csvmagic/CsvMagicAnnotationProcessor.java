package be.alferarui.csvmagic;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("be.alferarui.csvmagic.CsvMagicSerializable")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class CsvMagicAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("Processing annotations...");
        for (Element element : roundEnv.getElementsAnnotatedWith(CsvMagicSerializable.class)) {
            System.out.println("Found @be.alferarui.csvmagic.CsvMagicSerializable on " + element.getSimpleName());
            // Get the class name and package
            TypeElement classElement = (TypeElement) element;
            String className = classElement.getSimpleName().toString();
            String packageName = processingEnv.getElementUtils().getPackageOf(classElement).getQualifiedName().toString();

            // Generate the CSV file name
            String csvFileName = className + ".list.csv";

            // Find fields annotated with @Id
            List<String> idFields = new ArrayList<>();
            for (Element enclosed : element.getEnclosedElements()) {
                if (enclosed.getKind() == ElementKind.FIELD && enclosed.getAnnotation(CsvMagicId.class) != null) {
                    idFields.add(enclosed.getSimpleName().toString());
                }
            }

            // Generate a manager class for CSV storage
            generateCsvManagerClass(packageName, className, csvFileName, idFields);
        }
        return true;
    }

    private void generateCsvManagerClass(String packageName, String className, String csvFileName, List<String> idFields) {
        MethodSpec storeMethod = MethodSpec.methodBuilder("store")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(List.class, "objects")
                .addStatement("// Implement CSV writing logic here")
                .build();

        MethodSpec retrieveMethod = MethodSpec.methodBuilder("retrieve")
                .addModifiers(Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(ClassName.get(List.class), ClassName.get(packageName, className)))
                .addStatement("// Implement CSV reading logic here")
                .build();

        TypeSpec csvManager = TypeSpec.classBuilder(className + "CsvManager")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(storeMethod)
                .addMethod(retrieveMethod)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, csvManager)
                .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            System.out.println("Error writing the generated file...");
            e.printStackTrace();
        }
    }
}
