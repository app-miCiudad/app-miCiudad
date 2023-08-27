package miCiudad.modules.miCiudad.types;

import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Property(maxLength = Empresa.MAX_LEN, optionality = Optionality.OPTIONAL)
@Parameter(maxLength = Empresa.MAX_LEN, optionality = Optionality.OPTIONAL)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Empresa {

    int MAX_LEN = 60;
}
