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

import miCiudad.modules.miCiudad.types.FirstName;
import miCiudad.modules.miCiudad.types.LastName;

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
            @LastName final String lastName,
            @FirstName final String firstName) {
        return repositoryService.persist(Barrio.withName(lastName, firstName));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public List<Barrio> findByLastNameLike(
            @LastName final String lastName) {
        return repositoryService.allMatches(
                Query.named(Barrio.class, Barrio.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE)
                     .withParameter("lastName", "%" + lastName + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Barrio> findByLastName(
            @LastName final String lastName
            ) {
        return barrioRepository.findByLastNameContaining(lastName);
    }


    @Programmatic
    public Barrio findByLastNameExact(final String lastName) {
        return barrioRepository.findByLastName(lastName);
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
                        "SELECT p FROM Barrio p ORDER BY p.lastName",
                        Barrio.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }


}
