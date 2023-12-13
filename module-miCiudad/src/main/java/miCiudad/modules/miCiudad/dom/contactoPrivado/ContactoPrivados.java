package miCiudad.modules.miCiudad.dom.contactoPrivado;

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
        logicalTypeName = "miCiudad.ContactoPrivados"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class ContactoPrivados {
    final RepositoryService repositoryService;
    final ContactoPrivadoRepository contactoPrivadoRepository;
    
    
    ///// Listar todos los datos //////
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<ContactoPrivado> listAll() {
        return contactoPrivadoRepository.findAll();
    }
    ////////////////////////////////
}
