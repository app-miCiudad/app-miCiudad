package miCiudad.modules.miCiudad.types.TypesObra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.Property;

@Property(editing = Editing.ENABLED,maxLength = TituloObra.MAX_LEN, optionality = Optionality.MANDATORY)
@Parameter(maxLength = TituloObra.MAX_LEN, optionality = Optionality.MANDATORY)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TituloObra {

    int MAX_LEN = 200;
}
