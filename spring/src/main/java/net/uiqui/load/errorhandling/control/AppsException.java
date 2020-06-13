package net.uiqui.load.errorhandling.control;

public class AppsException extends RuntimeException {
    private final AppsError error;
    private final transient Object[] parameters;

    public AppsException(final AppsError error, final Object... parameters) {
        super(error.toString());
        this.error = error;
        this.parameters = parameters.clone();
    }

    public AppsException(final Throwable cause, final AppsError error, final Object... parameters) {
        super(error.toString(), cause);
        this.error = error;
        this.parameters = parameters.clone();
    }

    public AppsError getError() {
        return error;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public String getErrorDetailsWithParameters() {
        return error.getErrorDetails(parameters);
    }
}
