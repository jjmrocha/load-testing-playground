package net.uiqui.load.errorhandling.boundary;

import net.uiqui.load.errorhandling.control.AppsError;
import net.uiqui.load.errorhandling.control.AppsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class ErrorHandler extends ExceptionHandlerExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(value = AppsException.class)
    public ResponseEntity<ErrorDetail> handlePositionException(final AppsException ex, final WebRequest webRequest) {
        final AppsError error = ex.getError();
        final String details = ex.getErrorDetailsWithParameters();
        return buildResponse(webRequest, error, details, ex.getCause());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDetail> handleMissingParameter(final MissingServletRequestParameterException ex, final WebRequest webRequest) {
        final AppsError error = AppsError.MISSING_PARAMETER;
        final String details = error.getErrorDetails(ex.getParameterName());
        return buildResponse(webRequest, error, details, null);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDetail> handleMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final WebRequest webRequest) {
        final AppsError error = AppsError.METHOD_NOT_SUPPORTED;
        final String details = error.getErrorDetails(ex.getMethod());
        return buildResponse(webRequest, error, details, null);
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ErrorDetail> handleException(final Throwable ex, final WebRequest webRequest) {
        final AppsError error = AppsError.INTERNAL_SERVER_ERROR;
        final String details = error.getErrorDetails();
        return buildResponse(webRequest, error, details, ex);
    }

    private ResponseEntity<ErrorDetail> buildResponse(
            final WebRequest webRequest,
            final AppsError error,
            final String details,
            final Throwable cause) {
        final String resourceInstance = extractURI(webRequest.getDescription(false));
        final String logMessage = String.format("Status: %s, Type: %s, Details: %s, Instance: %s",
                String.valueOf(error.getResponseStatusCode()),
                error.getErrorType(),
                details,
                resourceInstance);

        if (error.getResponseStatusCode() >= 500) {
            if (cause == null) {
                LOG.error(logMessage);
            } else {
                LOG.error(logMessage, cause);
            }
        } else {
            LOG.info(logMessage);
        }

        final ErrorDetail errorDetail = ErrorDetail.Builder.anErrorDetail()
                .withType(error.getErrorType())
                .withDetail(details)
                .withInstance(resourceInstance)
                .build();

        return ResponseEntity
                .status(error.getResponseStatusCode())
                .body(errorDetail);
    }

    private String extractURI(final String text) {
        if (text == null) return null;
        final String[] parts = text.split("=");
        return parts.length == 2 ? parts[1] : null;
    }
}
