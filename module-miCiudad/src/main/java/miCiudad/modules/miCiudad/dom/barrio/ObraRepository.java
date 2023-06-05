package miCiudad.modules.miCiudad.dom.barrio;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface ObraRepository extends Repository<Obra, Long> {

    List<Obra> findByBarrio(Barrio barrio);
}
