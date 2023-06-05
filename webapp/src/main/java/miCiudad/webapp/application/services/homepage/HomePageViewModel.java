package miCiudad.webapp.application.services.homepage;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.Nature;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import miCiudad.modules.miCiudad.dom.barrio.Barrios;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        logicalTypeName = "simple.HomePageViewModel"
        )
@HomePage
@DomainObjectLayout()
public class HomePageViewModel {

    public String title() {
        return getBarrios().size() + " Barrios";
    }

    public List<Barrio> getBarrios() {
        return barrios.listAll();
    }

    @Inject Barrios barrios;
}
