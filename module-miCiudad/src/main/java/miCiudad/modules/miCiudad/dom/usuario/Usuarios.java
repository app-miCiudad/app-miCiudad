package miCiudad.modules.miCiudad.dom.usuario;
import java.util.List;
import javax.annotation.Priority;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import miCiudad.modules.miCiudad.dom.empresa.Empresa;
import miCiudad.modules.miCiudad.dom.empresa.EmpresaRepository;
import miCiudad.modules.miCiudad.types.Nombre;
import miCiudad.modules.miCiudad.types.TypesEmpresa.NombreEmpresa;
import miCiudad.modules.miCiudad.types.TypesUsuario.ContraseñaUsuario;
import miCiudad.modules.miCiudad.types.TypesUsuario.NombreUsuario;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;


@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "miCiudad.Usuarios"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Usuarios {
    final RepositoryService repositoryService;
    final UsuarioRepository usuarioRepository;
    final JpaSupportService jpaSupportService;

    /////// Constructores /////////////
    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    
    public Usuario create(
            @NombreUsuario final String nombre,
            @ContraseñaUsuario final String contraseña) {
                return repositoryService.persist(Usuario.withName(nombre,contraseña));
    }
    ////////////////////////////////

    ///// Listar todos los datos //////
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Usuario> listAll() {
        return usuarioRepository.findAll();
    }
    ////////////////////////////////

    
    ///// Buscar por nombre ///////////
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public Usuario findByNombre(
            @NombreEmpresa final String nombre
            ) {
        return usuarioRepository.findByNombre(nombre);
    }
    //////////////////
    
    
}
