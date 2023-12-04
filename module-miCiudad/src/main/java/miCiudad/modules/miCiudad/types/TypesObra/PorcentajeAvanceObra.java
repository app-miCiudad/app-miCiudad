package miCiudad.modules.miCiudad.types.TypesObra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Where;

@Property(editing = Editing.ENABLED, maxLength = PorcentajeAvanceObra.MAX_LEN, optionality = Optionality.OPTIONAL)
@PropertyLayout(named = "Porcentaje Avance", hidden = Where.ALL_TABLES)
@Parameter(maxLength = PorcentajeAvanceObra.MAX_LEN, optionality = Optionality.OPTIONAL)
@ParameterLayout(named = "Porcentaje Avance")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PorcentajeAvanceObra {

    int MAX_LEN = 4000;

}
