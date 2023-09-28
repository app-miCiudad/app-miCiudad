package miCiudad.modules.miCiudad.dom.obra;
import java.util.List;

import javax.annotation.Priority;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.dom.empresa.Empresa;

import javax.inject.Inject;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.SemanticsOf;


@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "miCiudad.Obras"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Obras {

    final RepositoryService repositoryService;
    final ObraRepository obraRepository;
    
        ///// Listar todos los datos //////
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Obra> listAll() {
        return obraRepository.findAll();
    }
    ////////////////////////////////
}
