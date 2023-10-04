package miCiudad.modules.miCiudad.dom.barrio;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.dom.obra.Obra;
import miCiudad.modules.miCiudad.dom.obra.ObraRepository;

import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;

import javax.inject.Inject;
import java.util.List;


@Collection
@CollectionLayout(defaultView = "table")
@RequiredArgsConstructor
public class Barrio_obras {

    
    private final Barrio barrio;

    public List<Obra> coll() {
        return obraRepository.findByBarrio(barrio);
    }

    @Inject
    ObraRepository obraRepository;
}

