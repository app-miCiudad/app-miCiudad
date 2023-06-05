package miCiudad.modules.miCiudad.fixture;

import javax.inject.Inject;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import miCiudad.modules.miCiudad.dom.barrio.Barrios;
import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class BarrioBuilder extends BuilderScriptWithResult<Barrio> {

    @Getter @Setter
    private String name;

    @Override
    protected Barrio buildResult(final ExecutionContext ec) {

        checkParam("name", ec, String.class);

        return wrap(barrios).create(name);
    }

    // -- DEPENDENCIES

    @Inject
    Barrios barrios;

}
