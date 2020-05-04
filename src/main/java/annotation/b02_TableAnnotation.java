package annotation;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface b02_TableAnnotation {

	String tableName() default "null";
}
