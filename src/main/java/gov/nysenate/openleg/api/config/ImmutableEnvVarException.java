package gov.nysenate.openleg.api.config;

import gov.nysenate.openleg.api.config.EnvironmentVariableView;

public class ImmutableEnvVarException extends RuntimeException {

    private static final long serialVersionUID = -2737492745999746361L;
    private EnvironmentVariableView var;

    public ImmutableEnvVarException(EnvironmentVariableView var) {
        super("Environment Variable " + var.getName() + " is immutable and cannot be modified");
        this.var = var;
    }

    public EnvironmentVariableView getVar() {
        return var;
    }
}
