package miCiudad.modules.miCiudad.dom.empresa;

import java.util.List;

import javax.inject.Inject;

import lombok.RequiredArgsConstructor;
import miCiudad.modules.miCiudad.dom.contactoPrivado.ContactoPrivado;
import miCiudad.modules.miCiudad.dom.contactoPrivado.ContactoPrivadoRepository;
import miCiudad.modules.miCiudad.dom.contactoPublico.ContactoPublico;
import miCiudad.modules.miCiudad.dom.contactoPublico.ContactoPublicoRepository;

import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;



@Collection
@CollectionLayout(defaultView = "table")
@RequiredArgsConstructor
public class Empresa_contactoPrivado {

    private final Empresa empresa;

    public List<ContactoPrivado> coll() {
        return empresaRepository.findByEmpresa(empresa);
    }

    @Inject
    ContactoPrivadoRepository empresaRepository;
    
}
