package miCiudad.modules.obras.dom.barrio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarrioRepository extends JpaRepository<Barrio, Long> {

    List<Barrio> findByLastNameContaining(final String name);

    Barrio findByLastName(final String name);

}
