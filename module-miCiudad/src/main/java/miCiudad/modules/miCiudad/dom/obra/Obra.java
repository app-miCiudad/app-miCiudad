package miCiudad.modules.miCiudad.dom.obra;

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

import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import miCiudad.modules.miCiudad.types.*;
import miCiudad.modules.miCiudad.types.TypesObra.FechaObra;
import miCiudad.modules.miCiudad.types.TypesObra.LatitudObra;
import miCiudad.modules.miCiudad.types.TypesObra.PresupuestoObra;
import miCiudad.modules.miCiudad.types.TypesObra.TipoObra;
import miCiudad.modules.miCiudad.types.TypesObra.TituloObra;

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
        @UniqueConstraint(name = "Obra_name__UNQ", columnNames = {"owner_id", "name"})
    }
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "miCiudad.Obra", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Obra implements Comparable<Obra> {

    ////////////////// Atributos//////////////
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "metadata", sequence = "1")
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "barrio_id")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private Barrio barrio;

    @TituloObra
    @Column(name = "titulo", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "titulo", sequence = "2")
    private String titulo;

    @Especificacion
    @Column(name ="especificacion", length = Especificacion.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "especificacion", sequence = "1")
    private String especificacion;

    @FechaObra
    @Column(name="fechaInicio", nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Fecha Inicio", sequence = "1")
    private DateTime fechaInicio;

    @FechaObra
    @Column(name="fechaFinal", nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Fecha Final", sequence = "1")
    private DateTime fechaFinal;

    @PresupuestoObra
    @Column(name="presupuesto", nullable=true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Presupuesto", sequence = "1")
    private double presupuesto;

    @LatitudObra
    @Column(name="latitud", nullable=false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Latitud", sequence = "1")
    private double latitud;

    @LatitudObra
    @Column(name="longitud", nullable=false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Longitud", sequence = "1")
    private double longitud;

    @TipoObra
    @Column(name="tipo", nullable=false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "Tipo Obra", sequence = "1")
    private String tipo;
    ///////////////////////////////

    //////////// Constructor ////////////
    public Obra(Barrio barrio, String titulo, String esp, DateTime fechaInicio, DateTime fechaFinal,
         double presupuesto, double latitud, double longitud, String tipo) {
        this.barrio = barrio;
        this.titulo = titulo;
        this.especificacion = esp;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.presupuesto = presupuesto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
    }
    /////////////////////////////////////

    private final static Comparator<Obra> comparator =
            Comparator.comparing(Obra::getBarrio).thenComparing(Obra::getTitulo);

    @Override
    public int compareTo(final Obra other) {
        return comparator.compare(this, other);
    }

}
