package miCiudad.modules.miCiudad.dom.barrio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarrioRepository extends JpaRepository<Barrio, Long> {

    /// Listar ///
    List<Barrio> findByNombreContaining(final String name);

    /// Buscar por nombre ///
    Barrio findByNombre(final String name);

}
