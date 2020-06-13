package net.uiqui.load.errorhandling.control;

import java.text.MessageFormat;

public enum AppsError {
    MISSING_PARAMETER(
            "missing-parameter",
            "Missing Parameter",
            "Parameter ''{0}'' is required",
            400),
    INVALID_PARAMETER(
            "invalid-parameter",
            "Invalid Parameter",
            "Invalid value {0} for ''{1}'', {2}",
            400),
    NOT_FOUND(
            "resource-not-found",
            "Resource not found",
            "Resource ''{0}'' doesn't exists",
            404),
    METHOD_NOT_SUPPORTED(
            "method-not-supported",
            "Method not Supported",
            "Method ''{0}'' not supported for this resource",
            405),
    INTERNAL_SERVER_ERROR(
            "unexpected-error",
            "Unexpected error",
            "Internal server error",
            500);

    private final String errorType;
    private final String errorTitle;
    private final String errorDetailsTemplate;
    private final int responseStatusCode;

    AppsError(final String errorType, String errorTitle, String errorDetailsTemplate, int responseStatusCode) {
        this.errorType = errorType;
        this.errorTitle = errorTitle;
        this.errorDetailsTemplate = errorDetailsTemplate;
        this.responseStatusCode = responseStatusCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorDetailsTemplate() {
        return errorDetailsTemplate;
    }

    public String getErrorDetails(final Object... parameters) {
        return MessageFormat.format(errorDetailsTemplate, parameters);
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    @Override
    public String toString() {
        return errorType + " - " + errorTitle;
    }
}
