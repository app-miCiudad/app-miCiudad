package miCiudad.modules.miCiudad.dom.usuario;
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

import miCiudad.modules.miCiudad.dom.empresa.Empresa;
import miCiudad.modules.miCiudad.types.Nombre;
import miCiudad.modules.miCiudad.types.TypesEmpresa.NombreEmpresa;
import miCiudad.modules.miCiudad.types.TypesUsuario.ContraseñaUsuario;
import miCiudad.modules.miCiudad.types.TypesUsuario.NombreUsuario;
import miCiudad.modules.miCiudad.types.TypesUsuario.TokenUsuario;

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
import miCiudad.modules.miCiudad.types.TypesUsuario.ContraseñaUsuario;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import java.util.concurrent.ThreadLocalRandom;

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
        @UniqueConstraint(name = "Usuario__nombre__UNQ", columnNames = {"nombre"})
    }
)

@NamedQueries({
        @NamedQuery(
                name = Usuario.NAMED_QUERY__FIND_BY_NOMBRE_LIKE,
                query = "SELECT so " +
                        "FROM Usuario so " +
                        "WHERE so.nombre LIKE :nombre"
        )
})

@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "miCiudad.Usuario", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Usuario {

    static final String NAMED_QUERY__FIND_BY_NOMBRE_LIKE = "Usuario.findByNombreLike";

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

    @NombreUsuario
    @Column(name = "nombre", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    private String nombre;

    @ContraseñaUsuario
    @Column(name = "contraseña", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "2")
    private String contraseña;

    @TokenUsuario
    @Column(name = "token", length = Nombre.MAX_LEN, nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "3")
    private String token;



    ////////////////////////////////

    ///// Muestra el titulo ////
    public String title() {
        return getNombre();
    }
    
    /////////////////

    //// Listar ////
    /* 
    public static Usuario withName(String name, String contraseña) {
        return withName(name, contraseña);
    }
*/
    public static Usuario withName(String nombre, String contraseña) {
        val simpleObject = new Usuario();
        simpleObject.setNombre(nombre);
        simpleObject.setContraseña(contraseña);

        String tokenAux = tokenGen(30);

        simpleObject.setToken(tokenAux);

        return simpleObject;
    }
    ////////////////////////
    /* 

    @Transient
    @PropertyLayout(fieldSetId = "name", sequence = "1", named = "Nombre del Usuario")
    public String getName() {
        return  getNombre();
    }
    public String nombre() {
        return getNombre();
    }
    */

    /* 
    ////// Actualizar atributos de la entidad ////

    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "1", named = "Editar Nombre")
    public Usuario updateName(
            @NombreUsuario final String nombre) {
        setNombre(nombre);
        return this;
    }
    public String default0UpdateName() {
        return getNombre();
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



    ////// Genera un Token aleatoreo //////

    public static int numeroAleatorioEnRango(int minimo, int maximo) {
    // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
    return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
}

    public static String tokenGen (int longitud) {
        // El banco de caracteres
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String cadena = "";
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    /////////////////////////////////////////
    
}
