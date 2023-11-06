package miCiudad.modules.miCiudad.dom.contactoPrivado;

import java.util.List;

import org.springframework.data.repository.Repository;


import miCiudad.modules.miCiudad.dom.empresa.Empresa;

public interface ContactoPrivadoRepository extends Repository<ContactoPrivado, Long>{

    //// Listar Empresa ////
    List<ContactoPrivado> findByEmpresa(Empresa empresa);
}
