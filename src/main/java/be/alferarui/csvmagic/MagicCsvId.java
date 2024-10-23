package be.alferarui.csvmagic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MagicCsvId {
    public String deserializer() default "String.valueOf";
    public String serializer() default "Object.toString";
}
