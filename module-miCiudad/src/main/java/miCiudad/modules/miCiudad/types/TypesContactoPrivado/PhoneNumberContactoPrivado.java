package miCiudad.modules.miCiudad.types.TypesContactoPrivado;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.Property;

@Property(
        editing = Editing.ENABLED,
        maxLength = PhoneNumberContactoPrivado.MAX_LEN,
        regexPattern = "[+]?[0-9 ]+",
        regexPatternReplacement =
                "Specify only numbers and spaces, optionally prefixed with '+'.  " +
                        "For example, '+353 1 555 1234', or '07123 456789'"
)
@Parameter(maxLength = PhoneNumberContactoPrivado.MAX_LEN)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberContactoPrivado {

    int MAX_LEN = 30;
}
