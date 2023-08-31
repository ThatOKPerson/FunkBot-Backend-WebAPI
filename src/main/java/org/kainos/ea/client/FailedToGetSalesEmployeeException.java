package org.kainos.ea.client;

public class FailedToGetSalesEmployeeException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get sales employee from the database!";
    }
}
