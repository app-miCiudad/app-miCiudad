package miCiudad.modules.miCiudad.dom.barrio;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.types.*;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;

import javax.inject.Inject;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "miCiudad")
@RequiredArgsConstructor
public class Barrio_addObra {
    private final Barrio barrio;

    public Barrio act(
            @Titulo final String titulo, @Especificacion final String esp,
            @Fecha final DateTime fechaInicio, @Fecha final DateTime fechaFinal,
            @Presupuesto double presupuesto, @Empresa final String empresa
            ) {
        repositoryService.persist(new Obra(barrio,titulo,esp,fechaInicio,fechaFinal, presupuesto, empresa));
        return barrio;
    }

    @Inject
    RepositoryService repositoryService;
}

