package miCiudad.modules.miCiudad.dom.contactoPublico;

import java.util.List;

import org.springframework.data.repository.Repository;


import miCiudad.modules.miCiudad.dom.empresa.Empresa;

public interface ContactoPublicoRepository extends Repository<ContactoPublico, Long>{
    
    ///// Listar ////
    List<ContactoPublico> findByEmpresa(Empresa empresa);
    
}
