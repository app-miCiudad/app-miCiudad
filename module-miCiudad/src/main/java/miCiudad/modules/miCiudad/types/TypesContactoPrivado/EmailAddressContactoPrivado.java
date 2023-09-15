package miCiudad.modules.miCiudad.types.TypesContactoPrivado;

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

@Property(
        editing = Editing.ENABLED,
        maxLength = EmailAddressContactoPrivado.MAX_LEN,
        regexPattern = "[^@]+@[^@]+[.][^@]+",  // should really use https://emailregex.com/
        regexPatternReplacement = "Invalid email address"
)
@PropertyLayout(named = "E-mail")
@Parameter(maxLength = EmailAddressContactoPrivado.MAX_LEN)
@ParameterLayout(named = "E-mail")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddressContactoPrivado {

    int MAX_LEN = 100;
}
