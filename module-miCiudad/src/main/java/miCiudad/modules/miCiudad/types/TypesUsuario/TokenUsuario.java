package miCiudad.modules.miCiudad.types.TypesUsuario;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;


@Property(maxLength = TokenUsuario.MAX_LEN,editing = Editing.ENABLED)
@Parameter(maxLength = TokenUsuario.MAX_LEN)
@ParameterLayout(named = "Token")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface TokenUsuario {
    int MAX_LEN = 40;
}
