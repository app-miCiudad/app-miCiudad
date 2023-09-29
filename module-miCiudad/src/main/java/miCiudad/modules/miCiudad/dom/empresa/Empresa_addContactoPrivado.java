package miCiudad.modules.miCiudad.dom.empresa;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.dom.contactoPrivado.ContactoPrivado;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.CargoContactoPrivado;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.EmailAddressContactoPrivado;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.NombreContactoPrivado;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.PhoneNumberContactoPrivado;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.repository.RepositoryService;

import javax.inject.Inject;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "miCiudad")
@RequiredArgsConstructor
public class Empresa_addContactoPrivado {

    private final Empresa empresa;

    public Empresa act(
            @NombreContactoPrivado final String nombre, @PhoneNumberContactoPrivado final String telefono,
            @EmailAddressContactoPrivado final String email, @CargoContactoPrivado final String cargo
            ) {
        repositoryService.persist(new ContactoPrivado(empresa,nombre,telefono,email,cargo));
        return empresa;
    }

    @Inject
    RepositoryService repositoryService;
    
}
