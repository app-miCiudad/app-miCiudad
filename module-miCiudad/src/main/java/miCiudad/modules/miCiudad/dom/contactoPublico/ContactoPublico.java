package miCiudad.modules.miCiudad.dom.contactoPublico;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import miCiudad.modules.miCiudad.dom.empresa.Empresa;
import miCiudad.modules.miCiudad.types.TypesContactoPublico.LinkContactoPublico;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;
import org.eclipse.persistence.annotations.DeleteAll;
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
        @UniqueConstraint(name = "ContactoPrivado__nombre__UNQ", columnNames = {"nombre"})
    }
)

@NamedQueries({
        @NamedQuery(
                name = ContactoPublico.NAMED_QUERY__FIND_BY_NOMBRE_LIKE,
                query = "SELECT so " +
                        "FROM Empresa so " +
                        "WHERE so.nombre LIKE :nombre"
        )
})

@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "miCiudad.ContactoPublico", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)

public class ContactoPublico {

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


    @LinkContactoPublico
    @Column(length = LinkContactoPublico.MAX_LEN, nullable = false, name = "link")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private String link;


    ////////////////////////////////
    public String title() {
        return getEmpresa().getName();
    }


    ///Constructor //////////////////
    public ContactoPublico(Empresa empresa, String link) {
        this.empresa = empresa;
        this.link = link;
    }
    ///////////////////////




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


    /// Listar ///
    public static ContactoPublico withLink(String name) {
        return withLink(name,null);
    }

    public static ContactoPublico withLink(String link, String nombre) {
        val simpleObject = new ContactoPublico();
        simpleObject.setLink(link);
        return simpleObject;
    }
    ////////////////
    
}
