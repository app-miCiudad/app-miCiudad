package miCiudad.webapp.custom.restapi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.isis.applib.services.iactnlayer.InteractionContext;
import org.apache.isis.applib.services.iactnlayer.InteractionService;
import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.applib.services.xactn.TransactionalProcessor;

import lombok.RequiredArgsConstructor;

import miCiudad.modules.miCiudad.dom.barrio.Barrio;
import miCiudad.modules.miCiudad.dom.barrio.Barrios;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Inject})
class CustomController {

    private final InteractionService interactionService;
    private final TransactionalProcessor transactionalProcessor;
    private final Barrios barrios;

    @GetMapping("/custom/simpleObjects")
    List<Barrio> all() {
        return call("sven", barrios::listAll)
                .orElse(Collections.<Barrio>emptyList());
    }

    private <T> Optional<T> call(
            final String username,
            final Callable<T> callable) {

        return interactionService.call(
                InteractionContext.ofUserWithSystemDefaults(UserMemento.ofName(username)),
                () -> transactionalProcessor.callWithinCurrentTransactionElseCreateNew(callable))
                .optionalElseFail(); // re-throws exception that has occurred, if any
    }

}
