package miCiudad.modules.miCiudad.dom.empresa;


import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


import miCiudad.modules.miCiudad.types.TypesEmpresa.NombreEmpresa;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;



@Entity
@Table(
    schema="miCiudad",
    uniqueConstraints = {
        @UniqueConstraint(name = "Empresa__nombre__UNQ", columnNames = {"nombre"})
    }
)

@NamedQueries({
        @NamedQuery(
                name = Empresa.NAMED_QUERY__FIND_BY_NOMBRE_LIKE,
                query = "SELECT so " +
                        "FROM Empresa so " +
                        "WHERE so.nombre LIKE :nombre"
        )
})

@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "miCiudad.Empresa", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)

public class Empresa {

    static final String NAMED_QUERY__FIND_BY_NOMBRE_LIKE = "Empresa.findByNombreLike";

    @Inject @Transient RepositoryService repositoryService;
    @Inject @Transient TitleService titleService;
    @Inject @Transient MessageService messageService;

    //// Atributos de la entidad /////
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "metadata", sequence = "1")
    private Long id;

    @NombreEmpresa
    @Column(length = NombreEmpresa.MAX_LEN, nullable = false, name = "nombre")
    @Getter @Setter @ToString.Include
    @Property(hidden = Where.EVERYWHERE)
    private String nombre;



    ////////////////////////////////
    public String title() {
        return getNombre();
    }


    public static Empresa withName(String name) {
        return withName(name, null);
    }

    public static Empresa withName(String nombre, String link) {
        val simpleObject = new Empresa();
        simpleObject.setNombre(nombre);
        return simpleObject;
    }

    


    public String nombre() {
        return getNombre();
    }
    

    

    @Transient
    @PropertyLayout(fieldSetId = "name", sequence = "1", named = "Nombre de la Empresa")
    public String getName() {
        return  getNombre();
    }

    





    ////// Actualizar atributos de la entidad ////

    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "1", named = "Editar Nombre")
    public Empresa updateName(
            @NombreEmpresa final String nombre) {
        setNombre(nombre);
        return this;
    }
    public String default0UpdateName() {
        return getNombre();
    }

    //////////////////////////////


    ///// Eliminar ////
    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(
            position = ActionLayout.Position.PANEL,
            describedAs = "Deletes this object from the persistent datastore")
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    }
    ////////////////////////////////

 
}
