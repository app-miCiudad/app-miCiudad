package miCiudad.modules.miCiudad.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.Property;

@Property(maxLength = Titulo.MAX_LEN, optionality = Optionality.MANDATORY)
@Parameter(maxLength = Titulo.MAX_LEN, optionality = Optionality.MANDATORY)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Titulo {

    int MAX_LEN = 60;
}
