package miCiudad.webapp.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import miCiudad.modules.miCiudad.ObrasModule;

@Configuration
@Import(ObrasModule.class)
@ComponentScan
public class ApplicationModule {

}
