package annotation;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface b03_ColumnAnnotation {

	String columnName();
}
