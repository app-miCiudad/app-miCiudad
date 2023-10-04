package miCiudad.modules.miCiudad.dom.empresa;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.dom.contactoPublico.ContactoPublico;
import miCiudad.modules.miCiudad.types.TypesContactoPublico.LinkContactoPublico;

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

public class Empresa_addContactoPublico {

    private final Empresa empresa;

    public Empresa act(
            @LinkContactoPublico final String link
            ) {
        repositoryService.persist(new ContactoPublico(empresa,link));
        return empresa;
    }

    @Inject
    RepositoryService repositoryService;
    
}
