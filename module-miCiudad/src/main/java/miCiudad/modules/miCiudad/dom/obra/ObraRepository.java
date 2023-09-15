package miCiudad.modules.miCiudad.dom.obra;

import org.springframework.data.repository.Repository;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;

import java.util.List;

public interface ObraRepository extends Repository<Obra, Long> {
    

    List<Obra> findByBarrio(Barrio barrio);
}
