package miCiudad.modules.obras.dom.barrio;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.obras.types.PetName;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import javax.inject.Inject;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "obras")
@RequiredArgsConstructor
public class Barrio_addObra {
    private final Barrio barrio;

    public Barrio act(
            @PetName final String name
    ) {
        repositoryService.persist(new Obra(barrio, name));
        return barrio;
    }

    @Inject
    RepositoryService repositoryService;
}

