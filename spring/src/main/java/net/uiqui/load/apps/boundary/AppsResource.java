package net.uiqui.load.apps.boundary;

import net.uiqui.load.apps.control.AppsStore;
import net.uiqui.load.apps.entity.Application;
import net.uiqui.load.errorhandling.control.AppsError;
import net.uiqui.load.errorhandling.control.AppsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
public class AppsResource {
    @Autowired
    private AppsStore appsStore;

    @GetMapping(
            value = {"/apps/{app_id}"},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Application> getApplication(@PathVariable("app_id") final int appId) {
        final Application application = appsStore.findByAppId(appId);

        if (isNull(application)) {
            throw new AppsException(AppsError.NOT_FOUND, appId);
        }

        return ResponseEntity.ok().body(application);
    }

    @PutMapping(
            value = {"/apps/{app_id}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Application> putApplication(
            @PathVariable("app_id") final int appId,
            @RequestBody final Application application
    ) {
        if (appId != application.getAppId()) {
            throw new AppsException(AppsError.INVALID_PARAMETER, appId, "app_id", "path doesn't match object");
        }

        if (appsStore.exists(appId)) {
            appsStore.update(application);
            return ResponseEntity.ok().body(application);
        } else {
            appsStore.create(application);
            return ResponseEntity.status(201).body(application);
        }
    }

    @DeleteMapping(value = {"/apps/{app_id}"})
    public void deleteApplication(@PathVariable("app_id") final int appId) {
        if (appsStore.exists(appId)) {
            appsStore.deleteByAppId(appId);
        }
    }
}
