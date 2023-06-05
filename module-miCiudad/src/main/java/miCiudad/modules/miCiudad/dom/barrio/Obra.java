package miCiudad.modules.miCiudad.dom.barrio;

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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import miCiudad.modules.miCiudad.types.*;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;


@Entity
@Table(
    schema="miCiudad",
    uniqueConstraints = {
        @UniqueConstraint(name = "Barrio_name__UNQ", columnNames = {"owner_id", "name"})
    }
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "miCiudad.Obra", entityChangePublishing = Publishing.ENABLED)
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



    Obra(Barrio barrio, String titulo, String esp, DateTime fechaInicio, DateTime fechaFinal,
         double presupuesto, String empresa) {
        this.barrio = barrio;
        this.titulo = titulo;
        this.especificacion = esp;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.presupuesto = presupuesto;
        this.empresa = empresa;
    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "barrio_id")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private Barrio barrio;

    @Titulo
    @Column(name = "titulo", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "titulo", sequence = "2")
    private String titulo;

    @Especificacion
    @Column(name ="especificacion", length = Especificacion.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "especificacion", sequence = "1")
    private String especificacion;

    @Fecha
    @Column(name="fechaInicio", nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Fecha Inicio", sequence = "1")
    private DateTime fechaInicio;

    @Fecha
    @Column(name="fechaFinal", nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Fecha Final", sequence = "1")
    private DateTime fechaFinal;

    @Presupuesto
    @Column(name="presupuesto", nullable=true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Presupuesto", sequence = "1")
    private double presupuesto;

    @Empresa
    @Column(name="empresa",nullable=true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Empresa", sequence = "1")
    private String empresa;


    private final static Comparator<Obra> comparator =
            Comparator.comparing(Obra::getBarrio).thenComparing(Obra::getTitulo);

    @Override
    public int compareTo(final Obra other) {
        return comparator.compare(this, other);
    }

}
