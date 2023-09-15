package miCiudad.modules.miCiudad.types.TypesEmpresa;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;


@Property(maxLength = NombreEmpresa.MAX_LEN)
@Parameter(maxLength = NombreEmpresa.MAX_LEN)
@ParameterLayout(named = "Nombre de la Empresa")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NombreEmpresa {

    int MAX_LEN = 40;
}
