package miCiudad.modules.miCiudad.fixture;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import miCiudad.modules.miCiudad.dom.barrio.Barrios;
import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Barrio_persona
implements PersonaWithBuilderScript<BarrioBuilder>, PersonaWithFinder<Barrio> {

    JONES("Jones"),
    FARRELL("Farrell"),
    UNDERHILL("Underhill"),
    FORD("Ford"),
    YOUNGS("Youngs"),
    MAY("May"),
    GENGE("Genge"),
    EWELS("Ewels"),
    VUNIPOLA("Vunipola"),
    ITOJE("Itoje");

    @Getter
    private final String name;

    @Override
    public BarrioBuilder builder() {
        return new BarrioBuilder().setName(name);
    }

    @Override
    public Barrio findUsing(final ServiceRegistry serviceRegistry) {
        Barrios barrios = serviceRegistry.lookupService(Barrios.class).orElse(null);
        return barrios.findByNombreExact(name);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<Barrio_persona, Barrio> {

        public PersistAll() {
            super(Barrio_persona.class);
        }
    }
}
