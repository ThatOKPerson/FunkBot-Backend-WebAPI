package org.kainos.ea.core;

import org.kainos.ea.cli.DeliveryEmployeeRequest;

public class DeliveryEmployeeValidator {
        public static String isValidDeliveryEmployee (DeliveryEmployeeRequest deliveryEmployee) {
            if (deliveryEmployee.getName().length() > 60) {
                return "Name greater than 60 characters";
            }

            if (deliveryEmployee.getNiNumber().length() != 9) {
                return "NINumber must be 9 characters in length";
            }

            if (deliveryEmployee.getBankAccNumber().length() != 8) {
                return "Bank Account Number must be 8 characters in length";
            }
            return null;
        }
}
