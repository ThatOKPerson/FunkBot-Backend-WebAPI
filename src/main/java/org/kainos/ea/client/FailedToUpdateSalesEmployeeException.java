package org.kainos.ea.client;

public class FailedToUpdateSalesEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to update Sales Employee";
    }
}
