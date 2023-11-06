package miCiudad.modules.miCiudad.dom.obra;

import org.springframework.data.jpa.repository.JpaRepository;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;

import java.util.List;

public interface ObraRepository extends JpaRepository<Obra, Long> {
    
    //// Listar ////
    List<Obra> findByBarrio(Barrio barrio);
}
