package miCiudad.modules.miCiudad.types.TypesObra;


import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Property(editing = Editing.ENABLED,maxLength = PresupuestoObra.MAX_LEN, optionality = Optionality.OPTIONAL)
@Parameter(maxLength = PresupuestoObra.MAX_LEN, optionality = Optionality.OPTIONAL)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PresupuestoObra {

    int MAX_LEN = 60;

}
