package miCiudad.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.extensions.flyway.impl.IsisModuleExtFlywayImpl;
import org.apache.isis.persistence.jpa.eclipselink.IsisModulePersistenceJpaEclipselink;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
import org.apache.isis.viewer.wicket.viewer.IsisModuleViewerWicketViewer;

import miCiudad.webapp.application.ApplicationModule;
import miCiudad.webapp.application.fixture.scenarios.miCiudadDemo;
import miCiudad.webapp.custom.CustomModule;
import miCiudad.webapp.quartz.QuartzModule;

@Configuration
@Import({
        IsisModuleCoreRuntimeServices.class,
        IsisModuleSecurityShiro.class,
        IsisModulePersistenceJpaEclipselink.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleTestingFixturesApplib.class,
        IsisModuleTestingH2ConsoleUi.class,

        IsisModuleExtFlywayImpl.class,

        ApplicationModule.class,
        CustomModule.class,
        QuartzModule.class,

        // discoverable fixtures
        miCiudadDemo.class
})
@PropertySources({
        @PropertySource(IsisPresets.DebugDiscovery),
})
public class AppManifest {
}
