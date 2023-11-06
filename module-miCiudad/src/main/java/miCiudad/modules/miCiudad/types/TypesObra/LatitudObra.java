package miCiudad.modules.miCiudad.types.TypesObra;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Property(editing = Editing.ENABLED,maxLength = LatitudObra.MAX_LEN)
@Parameter(maxLength = LatitudObra.MAX_LEN)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LatitudObra {
    int MAX_LEN = 900000000;
}
