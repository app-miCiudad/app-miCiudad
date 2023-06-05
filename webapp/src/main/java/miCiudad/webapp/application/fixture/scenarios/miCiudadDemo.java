package miCiudad.webapp.application.fixture.scenarios;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixturesService;

import miCiudad.modules.miCiudad.fixture.Barrio_persona;

public class miCiudadDemo extends FixtureScript {

    @Override
    protected void execute(final ExecutionContext ec) {
        ec.executeChildren(this, moduleWithFixturesService.getTeardownFixture());
        ec.executeChild(this, new Barrio_persona.PersistAll());
    }

    @Inject ModuleWithFixturesService moduleWithFixturesService;

}
