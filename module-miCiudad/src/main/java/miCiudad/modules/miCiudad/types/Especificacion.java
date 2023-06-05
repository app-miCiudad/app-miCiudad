package miCiudad.modules.miCiudad.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;

@Property(editing = Editing.ENABLED, maxLength = Especificacion.MAX_LEN)
@PropertyLayout(named = "Especificacion", multiLine = 10, hidden = Where.ALL_TABLES)
@Parameter(maxLength = Especificacion.MAX_LEN)
@ParameterLayout(named = "Especificacion", multiLine = 10)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Especificacion {

    int MAX_LEN = 4000;

}
