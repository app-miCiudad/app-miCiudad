package miCiudad.modules.miCiudad.dom.empresa;

import java.util.List;
import javax.annotation.Priority;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;
import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.types.TypesEmpresa.NombreEmpresa;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;


@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "miCiudad.Empresas"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Empresas {

    final RepositoryService repositoryService;
    final EmpresaRepository empresaRepository;
    final JpaSupportService jpaSupportService;

    /////// Constructores /////////////
    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public Empresa create(
            @NombreEmpresa final String nombre) {
        return repositoryService.persist(Empresa.withName(nombre));
    }
    ////////////////////////////////

    ///// Listar todos los datos //////
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Empresa> listAll() {
        return empresaRepository.findAll();
    }
    ////////////////////////////////

    //// Buscar por nombre ////
    ///// Buscar por nombre ///////////
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public Empresa findByNombre(
            @NombreEmpresa final String nombre
            ) {
        return empresaRepository.findByNombre(nombre);
    }
    //////////////////
    
}
