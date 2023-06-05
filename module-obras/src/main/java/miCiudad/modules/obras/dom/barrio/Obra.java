package miCiudad.modules.obras.dom.barrio;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import miCiudad.modules.obras.types.FirstName;
import miCiudad.modules.obras.types.PetName;


@Entity
@Table(
    schema="obras",
    uniqueConstraints = {
        @UniqueConstraint(name = "Barrio_name__UNQ", columnNames = {"owner_id", "name"})
    }
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "obras.Obra", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Obra implements Comparable<Obra> {

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


    Obra(Barrio barrio, String name) {
        this.barrio = barrio;
        this.name = name;
    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private Barrio barrio;

    @PetName
    @Column(name = "name", length = FirstName.MAX_LEN, nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "2")
    private String name;


    private final static Comparator<Obra> comparator =
            Comparator.comparing(Obra::getBarrio).thenComparing(Obra::getName);

    @Override
    public int compareTo(final Obra other) {
        return comparator.compare(this, other);
    }

}
