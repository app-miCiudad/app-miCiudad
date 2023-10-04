package miCiudad.modules.miCiudad.dom.obra;

import java.util.Comparator;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import miCiudad.modules.miCiudad.dom.empresa.Empresa;
import miCiudad.modules.miCiudad.dom.empresa.EmpresaRepository;
import miCiudad.modules.miCiudad.types.*;
import miCiudad.modules.miCiudad.types.TypesEmpresa.NombreEmpresa;
import miCiudad.modules.miCiudad.types.TypesObra.FechaObra;
import miCiudad.modules.miCiudad.types.TypesObra.LatitudObra;
import miCiudad.modules.miCiudad.types.TypesObra.PresupuestoObra;
import miCiudad.modules.miCiudad.types.TypesObra.TipoObra;
import miCiudad.modules.miCiudad.types.TypesObra.TituloObra;
import miCiudad.modules.miCiudad.types.TypesObra.TyEstadoObra;

import org.apache.isis.applib.annotation.*;
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
    @Inject @Transient EmpresaRepository empresaRepository;
    @Inject @Transient RepositoryService repositoryService;
    @Inject @Transient TitleService titleService;
    @Inject @Transient MessageService messageService;
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

    @OneToOne(optional = true)
    @JoinColumn(name = "empresa_id")
    @PropertyLayout(fieldSetId = "name", sequence = "2")
    @Getter @Setter
    private Empresa empresa;


    @TituloObra
    @Column(name = "titulo", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "3")
    private String titulo;

    @Especificacion
    @Column(name ="especificacion", length = Especificacion.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "4")
    private String especificacion;

    @FechaObra
    @Column(name="fechaInicio", nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "5")
    private DateTime fechaInicio;

    @FechaObra
    @Column(name="fechaFinal", nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "6")
    private DateTime fechaFinal;

    @PresupuestoObra
    @Column(name="presupuesto", nullable=true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "7")
    private double presupuesto;

    @LatitudObra
    @Column(name="latitud", nullable=false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "8")
    private double latitud;

    @LatitudObra
    @Column(name="longitud", nullable=false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "9")
    private double longitud;

    @TipoObra
    @Column(name="tipo", nullable=false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "10")
    private String tipo;

    @TyEstadoObra
    @Column(name ="TyEstadoObra", length = TyEstadoObra.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "11")
    private String estado;
    ///////////////////////////////

    //////////// Constructor ////////////
    public Obra(Barrio barrio, String titulo, String esp, DateTime fechaInicio, DateTime fechaFinal,
         double presupuesto, double latitud, double longitud, String tipo, String estado) {
        this.barrio = barrio;
        this.titulo = titulo;
        this.especificacion = esp;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.presupuesto = presupuesto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
        this.estado = estado;
    }
    /////////////////////////////////////

    public String title() {
        return getTitulo();
    }

    private final static Comparator<Obra> comparator =
            Comparator.comparing(Obra::getBarrio).thenComparing(Obra::getTitulo);

    @Override
    public int compareTo(final Obra other) {
        return comparator.compare(this, other);
    }

    ////// Actualizar atributos de la entidad ////

    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "empresa_id")
    public Obra updateEmpresa(
            @NombreEmpresa final String nombre) {
        
        Empresa em = empresaRepository.findByNombre(nombre);
        setEmpresa(em);
        return this;
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
