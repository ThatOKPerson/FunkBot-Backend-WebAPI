package org.kainos.ea.client;

public class FailedToCreateDeliveryEmployeeException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to create Delivery Employee";
    }
}
