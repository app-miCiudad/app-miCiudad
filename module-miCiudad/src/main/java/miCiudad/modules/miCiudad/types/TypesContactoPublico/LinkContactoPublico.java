package miCiudad.modules.miCiudad.types.TypesContactoPublico;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;

@Property(editing = Editing.ENABLED, maxLength = LinkContactoPublico.MAX_LEN)
@Parameter(maxLength = LinkContactoPublico.MAX_LEN)
@ParameterLayout(named = "Link Empresa", multiLine = 10)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface LinkContactoPublico {
    int MAX_LEN = 4000;
}
