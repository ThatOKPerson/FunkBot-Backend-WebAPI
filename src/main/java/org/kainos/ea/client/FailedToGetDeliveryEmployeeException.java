package org.kainos.ea.client;

public class FailedToGetDeliveryEmployeeException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get Delivery Employee";
    }
}
