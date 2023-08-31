package org.kainos.ea.core;

import org.kainos.ea.cli.SalesEmployee;

import java.sql.SQLException;

public class SalesEmployeeValidator {
    public static String isValidSalesEmployee(SalesEmployee salesEmployee) {
        if (salesEmployee.getName().length() > 60){
            return "name greater than 60 characters";
        }

        if (salesEmployee.getBankAccNumber().length() != 8){
            return "Bank Account Number must be 8 characters";
        }

        if (salesEmployee.getNiNumber().length() != 9){
            return "National Insurance Number must be 9 characters";
        }
        return null;
    }
}
