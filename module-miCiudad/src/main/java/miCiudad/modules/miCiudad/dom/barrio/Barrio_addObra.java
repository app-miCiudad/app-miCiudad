package miCiudad.modules.miCiudad.dom.barrio;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.dom.obra.EnumEstado;
import miCiudad.modules.miCiudad.dom.obra.Obra;
import miCiudad.modules.miCiudad.types.*;
import miCiudad.modules.miCiudad.types.TypesObra.FechaObra;
import miCiudad.modules.miCiudad.types.TypesObra.IdEmpresa;
import miCiudad.modules.miCiudad.types.TypesObra.LatitudObra;
import miCiudad.modules.miCiudad.types.TypesObra.PresupuestoObra;
import miCiudad.modules.miCiudad.types.TypesObra.TipoObra;
import miCiudad.modules.miCiudad.types.TypesObra.TituloObra;
import miCiudad.modules.miCiudad.types.TypesObra.TyEstadoObra;
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

    ///// Boton de agregar Obras en Barrio /////
    public Barrio act(
            @TituloObra final String titulo, @Especificacion final String esp,
            @FechaObra final String fechaInicio, @FechaObra final String fechaFinal,
            @PresupuestoObra final String presupuesto, @LatitudObra final String latitud,
            @LatitudObra final String longitud, @TipoObra final String tipo, 
            @TyEstadoObra final String estado, final String idEmpresa
            ) {
        repositoryService.persist(new Obra(barrio,titulo,esp,fechaInicio,fechaFinal, presupuesto,
        latitud,longitud,tipo,estado));
        return barrio;
    }
    /////////////////////////////////////////////

    @Inject
    RepositoryService repositoryService;
}

