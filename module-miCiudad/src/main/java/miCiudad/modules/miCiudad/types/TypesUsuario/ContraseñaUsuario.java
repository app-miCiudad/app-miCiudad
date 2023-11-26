package miCiudad.modules.miCiudad.types.TypesUsuario;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;


@Property(maxLength = ContraseñaUsuario.MAX_LEN,editing = Editing.ENABLED)
@Parameter(maxLength = ContraseñaUsuario.MAX_LEN)
@ParameterLayout(named = "Contraseña del Usuario")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContraseñaUsuario {

    int MAX_LEN = 40;
    
}
