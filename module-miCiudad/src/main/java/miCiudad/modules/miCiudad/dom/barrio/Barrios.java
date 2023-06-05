package miCiudad.modules.miCiudad.dom.barrio;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;

import lombok.RequiredArgsConstructor;

import miCiudad.modules.miCiudad.types.Nombre;

@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "miCiudad.Barrios"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Barrios {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final BarrioRepository barrioRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public Barrio create(
            @Nombre final String nombre) {
        return repositoryService.persist(Barrio.withName(nombre));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public List<Barrio> findByNombreLike(
            @Nombre final String nombre) {
        return repositoryService.allMatches(
                Query.named(Barrio.class, Barrio.NAMED_QUERY__FIND_BY_NOMBRE_LIKE)
                     .withParameter("nombre", "%" + nombre + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Barrio> findByNombre(
            @Nombre final String nombre
            ) {
        return barrioRepository.findByNombreContaining(nombre);
    }


    @Programmatic
    public Barrio findByNombreExact(final String nombre) {
        return barrioRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Barrio> listAll() {
        return barrioRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Barrio.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Barrio> q = entityManager.createQuery(
                        "SELECT p FROM Barrio p ORDER BY p.nombre",
                        Barrio.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }


}
