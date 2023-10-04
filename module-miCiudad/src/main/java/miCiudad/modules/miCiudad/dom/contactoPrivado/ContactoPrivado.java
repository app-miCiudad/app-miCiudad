package miCiudad.modules.miCiudad.dom.contactoPrivado;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.eclipse.persistence.annotations.DeleteAll;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import miCiudad.modules.miCiudad.dom.empresa.Empresa;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.CargoContactoPrivado;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.EmailAddressContactoPrivado;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.NombreContactoPrivado;
import miCiudad.modules.miCiudad.types.TypesContactoPrivado.PhoneNumberContactoPrivado;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(
    schema="miCiudad",
    uniqueConstraints = {
        @UniqueConstraint(name = "ContactoPrivado__nombre__UNQ", columnNames = {"nombre"})
    }
)

@NamedQueries({
        @NamedQuery(
                name = ContactoPrivado.NAMED_QUERY__FIND_BY_NOMBRE_LIKE,
                query = "SELECT so " +
                        "FROM Empresa so " +
                        "WHERE so.nombre LIKE :nombre"
        )
})

@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "miCiudad.ContactoPrivado", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)

public class ContactoPrivado {

        static final String NAMED_QUERY__FIND_BY_NOMBRE_LIKE = "ContactoPrivado.findByNombreLike";

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

    @ManyToOne(optional = false)
    @DeleteAll
    @JoinColumn(name = "empresa_id")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private Empresa empresa;


    @NombreContactoPrivado
    @Column(length = NombreContactoPrivado.MAX_LEN, nullable = false, name = "nombre")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private String nombre;

    @PhoneNumberContactoPrivado
    @Column(length = PhoneNumberContactoPrivado.MAX_LEN, nullable = false, name = "telefono")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private String telefono;

    
    @EmailAddressContactoPrivado
    @Column(length = EmailAddressContactoPrivado.MAX_LEN, nullable = false, name = "email")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private String email;


    @CargoContactoPrivado
    @Column(length = CargoContactoPrivado.MAX_LEN, nullable = false, name = "cargo")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private String cargo;
    


    ////////////////////////////////
    public String title() {
        return getNombre();
    }


    ///Constructor //////////////////
    public ContactoPrivado(Empresa empresa, String nombre, String telefono, String email,
    String cargo) {
        this.empresa = empresa;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.cargo = cargo;
    }
    ///////////////////////


    public static ContactoPrivado withLink(String name) {
        return withLink(name,null);
    }

    public static ContactoPrivado withLink(String link, String nombre) {
        val simpleObject = new ContactoPrivado();
        simpleObject.setNombre(link);
        return simpleObject;
    }

    


/* 
    
    public String link() {
        return getLink();
    }
    
    @Transient
    @PropertyLayout(fieldSetId = "name", sequence = "1", named = "Link")
    public String getLinkAux() {
        return  getLink();
    }

*/


/* 
    ////// Actualizar atributos de la entidad ////
    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "2", named = "Editar Link")
    public ContactoPublico updateLink(
            @LinkContactoPublico final String link) {
        setLink(link);
        return this;
    }
    public String default0UpdateLink() {
        return getLink();
    }

    //////////////////////////////
*/

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
