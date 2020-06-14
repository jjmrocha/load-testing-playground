package net.uiqui.load.apps.boundary;

import net.uiqui.load.apps.control.ApplicationStore;
import net.uiqui.load.apps.entity.Application;
import net.uiqui.load.errorhandling.control.AppsError;
import net.uiqui.load.errorhandling.control.AppsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AppsResource {
    @Autowired
    private ApplicationStore applicationStore;

    @GetMapping(value = "/apps/{app_id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Application> getApplication(@PathVariable("app_id") final int appId) {
        final Optional<Application> optionalApp = applicationStore.findById(appId);

        if (optionalApp.isEmpty()) {
            throw new AppsException(AppsError.NOT_FOUND, appId);
        }

        return ResponseEntity.ok().body(optionalApp.get());
    }

    @PutMapping(value = "/apps/{app_id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Application> saveApplication(
            @PathVariable("app_id") final int appId,
            @RequestBody final Application application
    ) {
        if (appId != application.getAppId()) {
            throw new AppsException(AppsError.INVALID_PARAMETER, appId, "app_id", "path doesn't match object");
        }

        final Optional<Application> optionalApp = applicationStore.findById(appId);

        if (optionalApp.isPresent()) {
            final Application oldApp = optionalApp.get();
            oldApp.setName(application.getName());
            final Application savedApp = applicationStore.save(oldApp);
            return ResponseEntity.ok().body(savedApp);
        }

        final Application savedApp = applicationStore.save(application);
        return ResponseEntity.status(CREATED).body(savedApp);
    }

    @DeleteMapping(value = "/apps/{app_id}")
    public void deleteApplication(@PathVariable("app_id") final int appId) {
        applicationStore.findById(appId).ifPresent(applicationStore::delete);
    }
}
