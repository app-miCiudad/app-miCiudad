package miCiudad.modules.miCiudad.dom.empresa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    List<Empresa> findByNombreContaining(final String name);

    Empresa findByNombre(final String name);

}
