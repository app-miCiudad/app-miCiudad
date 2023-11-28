package miCiudad.modules.miCiudad.dom.usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /// Listar ///
    List<Usuario> findByNombreContaining(final String name);

    //// Buscador ///
    Usuario findByNombre(final String name);



    
}
