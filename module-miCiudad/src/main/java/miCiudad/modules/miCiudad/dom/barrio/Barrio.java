package miCiudad.modules.miCiudad.dom.barrio;

import java.util.Comparator;

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
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import miCiudad.modules.miCiudad.types.*;
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

import miCiudad.modules.miCiudad.types.Especificacion;


@Entity
@Table(
    schema="miCiudad",
    uniqueConstraints = {
        @UniqueConstraint(name = "Barrio__lastName__UNQ", columnNames = {"LASTNAME"})
    }
)
@NamedQueries({
        @NamedQuery(
                name = Barrio.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE,
                query = "SELECT so " +
                        "FROM Barrio so " +
                        "WHERE so.lastName LIKE :lastName"
        )
})
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "miCiudad.Barrio", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Barrio implements Comparable<Barrio> {

    static final String NAMED_QUERY__FIND_BY_LAST_NAME_LIKE = "Barrio.findByLastNameLike";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "metadata", sequence = "1")
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    @PropertyLayout(fieldSetId = "metadata", sequence = "999")
    @Getter @Setter
    private long version;

    public static Barrio withName(String name) {
        return withName(name, null);
    }

    public static Barrio withName(String lastName, String firstName) {
        val simpleObject = new Barrio();
        simpleObject.setLastName(lastName);
        simpleObject.setFirstName(firstName);
        return simpleObject;
    }

    @Inject @Transient RepositoryService repositoryService;
    @Inject @Transient TitleService titleService;
    @Inject @Transient MessageService messageService;


    public String title() {
        return getLastName() + (getFirstName() != null ? ", " + getFirstName() : "");
    }

    @Transient
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    public String getName() {
        return (getFirstName() != null ? getFirstName() + " ": "")  + getLastName();
    }

    @LastName
    @Column(length = LastName.MAX_LEN, nullable = false)
    @Getter @Setter @ToString.Include
    @Property(hidden = Where.EVERYWHERE)
    private String lastName;

    @FirstName
    @Column(length = FirstName.MAX_LEN, nullable = true)
    @Getter @Setter @ToString.Include
    @Property(hidden = Where.EVERYWHERE)
    private String firstName;


    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "name")
    public Barrio updateName(
            @LastName final String lastName,
            @FirstName final String firstName) {
        setLastName(lastName);
        setFirstName(firstName);
        return this;
    }
    public String default0UpdateName() {
        return getLastName();
    }
    public String default1UpdateName() {
        return getFirstName();
    }


    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(
            associateWith = "lastName", position = ActionLayout.Position.PANEL,
            describedAs = "Deletes this object from the persistent datastore")
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    }



    private final static Comparator<Barrio> comparator =
            Comparator.comparing(Barrio::getLastName);

    @Override
    public int compareTo(final Barrio other) {
        return comparator.compare(this, other);
    }

}
