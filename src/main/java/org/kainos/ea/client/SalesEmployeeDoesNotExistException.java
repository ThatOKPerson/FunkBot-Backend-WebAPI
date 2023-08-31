package org.kainos.ea.client;

public class SalesEmployeeDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Sales employee doesn't exist in the database";
    }
}
