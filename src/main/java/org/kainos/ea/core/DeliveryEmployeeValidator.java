package org.kainos.ea.core;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.GenericValidationException;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class DeliveryEmployeeValidator {

    public void isValidDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) throws GenericValidationException {
        if (deliveryEmployee.getFirstName().length() > 70) {
            throw new GenericValidationException("First name must be less than 70 characters");
        }

        if (deliveryEmployee.getLastName().length() > 70) {
            throw new GenericValidationException("Last name must be less than 70 characters");
        }

        if (deliveryEmployee.getSalary() < 0) {
            throw new GenericValidationException("Salary must be more than 0");
        }

        if (deliveryEmployee.getSalary() > 9999999.99) {
            throw new GenericValidationException("Salary must not be more than 9 999 999.99");
        }

        if (deliveryEmployee.getBankAccountNumber().length() > 8) {
            throw new GenericValidationException("Bank account number must be less than 8 characters");
        }

        if (deliveryEmployee.getNationalInsuranceNumber().length() > 9) {
            throw new GenericValidationException("National insurance number must be less than 9 characters");
        }
    }
}
