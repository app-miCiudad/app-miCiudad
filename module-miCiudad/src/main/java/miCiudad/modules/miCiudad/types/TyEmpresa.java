package miCiudad.modules.miCiudad.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.spec.AbstractSpecification;

@Property(maxLength = TyEmpresa.MAX_LEN, mustSatisfy = TyEmpresa.Spec.class)
@Parameter(maxLength = TyEmpresa.MAX_LEN, mustSatisfy = TyEmpresa.Spec.class)
@ParameterLayout(named = "Nombre de la Empresa")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TyEmpresa {

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
