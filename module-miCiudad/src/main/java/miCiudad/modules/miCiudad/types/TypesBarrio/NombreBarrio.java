package miCiudad.modules.miCiudad.types.TypesBarrio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.spec.AbstractSpecification;

@Property(maxLength = NombreBarrio.MAX_LEN, mustSatisfy = NombreBarrio.Spec.class, editing = Editing.ENABLED)
@Parameter(maxLength = NombreBarrio.MAX_LEN, mustSatisfy = NombreBarrio.Spec.class)
@ParameterLayout(named = "Nombre del Barrio")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NombreBarrio {

    int MAX_LEN = 40;

    class Spec extends AbstractSpecification<String> {
        @Override public String satisfiesSafely(String candidate) {
            for (char prohibitedCharacter : "&%$!".toCharArray()) {
                if( candidate.contains(""+prohibitedCharacter)) {
                    return "Character '" + prohibitedCharacter + "' is not allowed.";
                }
            }
            return null;
        }
    }
}
