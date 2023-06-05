package miCiudad.modules.miCiudad;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EntityScan(basePackageClasses = {ObrasModule.class})
public class ObrasModule implements ModuleWithFixtures {

    @Override
    public FixtureScript getTeardownFixture() {
        return new FixtureScript() {
            @Override
            protected void execute(ExecutionContext executionContext) {
                repositoryService.removeAll(Barrio.class);
            }
        };
    }
}
