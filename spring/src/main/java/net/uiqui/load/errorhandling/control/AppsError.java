package net.uiqui.load.errorhandling.control;

import java.text.MessageFormat;

public enum AppsError {
    MISSING_PARAMETER(
            "missing-parameter",
            "Parameter ''{0}'' is required",
            400),
    INVALID_PARAMETER(
            "invalid-parameter",
            "Invalid value {0} for ''{1}'', {2}",
            400),
    NOT_FOUND(
            "resource-not-found",
            "Resource ''{0}'' doesn't exists",
            404),
    METHOD_NOT_SUPPORTED(
            "method-not-supported",
            "Method ''{0}'' not supported for this resource",
            405),
    INTERNAL_SERVER_ERROR(
            "unexpected-error",
            "Internal server error",
            500);

    private final String errorType;
    private final String errorDetailsTemplate;
    private final int responseStatusCode;

    AppsError(final String errorType, String errorDetailsTemplate, int responseStatusCode) {
        this.errorType = errorType;
        this.errorDetailsTemplate = errorDetailsTemplate;
        this.responseStatusCode = responseStatusCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorDetails(final Object... parameters) {
        return MessageFormat.format(errorDetailsTemplate, parameters);
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }
}
