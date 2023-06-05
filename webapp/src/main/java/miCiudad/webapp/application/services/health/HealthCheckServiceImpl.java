package miCiudad.webapp.application.services.health;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import org.apache.isis.applib.services.health.Health;
import org.apache.isis.applib.services.health.HealthCheckService;

import miCiudad.modules.miCiudad.dom.barrio.Barrios;

@Service
@Named("miCiudad.HealthCheckServiceImpl")
public class HealthCheckServiceImpl implements HealthCheckService {

    private final Barrios barrios;

    @Inject
    public HealthCheckServiceImpl(Barrios barrios) {
        this.barrios = barrios;
    }

    @Override
    public Health check() {
        try {
            barrios.ping();
            return Health.ok();
        } catch (Exception ex) {
            return Health.error(ex);
        }
    }
}
