package miCiudad.modules.miCiudad.dom.contactoPublico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import miCiudad.modules.miCiudad.dom.empresa.Empresa;

public interface ContactoPublicoRepository extends JpaRepository<ContactoPublico, Long>{
    
    ///// Listar ////
    List<ContactoPublico> findByEmpresa(Empresa empresa);
    
}
