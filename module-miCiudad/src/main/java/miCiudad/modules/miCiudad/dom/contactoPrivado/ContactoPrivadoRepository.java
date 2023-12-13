package miCiudad.modules.miCiudad.dom.contactoPrivado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import miCiudad.modules.miCiudad.dom.empresa.Empresa;

public interface ContactoPrivadoRepository extends JpaRepository<ContactoPrivado, Long>{

    //// Listar Empresa ////
    List<ContactoPrivado> findByEmpresa(Empresa empresa);
}
