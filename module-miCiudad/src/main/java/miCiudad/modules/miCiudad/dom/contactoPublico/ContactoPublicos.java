package miCiudad.modules.miCiudad.dom.contactoPublico;

import java.util.List;

import javax.annotation.Priority;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.SemanticsOf;


@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "miCiudad.ContactoPublicos"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )

public class ContactoPublicos {
    final RepositoryService repositoryService;
    final ContactoPublicoRepository contactoPublicoRepository;
    
    
    ///// Listar todos los datos //////
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<ContactoPublico> listAll() {
        return contactoPublicoRepository.findAll();
    }
    ////////////////////////////////
}
