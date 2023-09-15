package miCiudad.modules.miCiudad.types.TypesContactoPrivado;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.spec.AbstractSpecification;

@Property(maxLength = CargoContactoPrivado.MAX_LEN, mustSatisfy = CargoContactoPrivado.Spec.class,editing = Editing.ENABLED)
@Parameter(maxLength = CargoContactoPrivado.MAX_LEN, mustSatisfy = CargoContactoPrivado.Spec.class)
@ParameterLayout(named = "Cargo")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CargoContactoPrivado {

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
