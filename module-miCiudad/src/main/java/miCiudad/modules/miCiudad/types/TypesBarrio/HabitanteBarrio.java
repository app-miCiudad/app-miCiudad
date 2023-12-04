package miCiudad.modules.miCiudad.types.TypesBarrio;
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

@Property(maxLength = HabitanteBarrio.MAX_LEN, editing = Editing.ENABLED)
@Parameter(maxLength = HabitanteBarrio.MAX_LEN)
@ParameterLayout(named = "Habitantes")
@PropertyLayout(named = "Habitantes", multiLine = 1, hidden = Where.ALL_TABLES)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface HabitanteBarrio {
    int MAX_LEN = 40;
}
