package org.kainos.ea.core;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.DeliveryEmployeeUpdateRequest;
import org.kainos.ea.client.GenericValidationException;

public class EmployeeValidator {

    private void checkFirstName(String firstName) throws GenericValidationException {
        if (firstName.length() > 70) {
            throw new GenericValidationException("First name must be less than 70 characters");
        }
    }

    private void checkLastName(String lastName) throws GenericValidationException {
        if (lastName.length() > 70) {
            throw new GenericValidationException("Last name must be less than 70 characters");
        }
    }

    private void checkSalary(double salary) throws GenericValidationException {
        if (salary < 0) {
            throw new GenericValidationException("Salary must be more than 0");
        }

        if (salary > 9999999.99) {
            throw new GenericValidationException("Salary must not be more than 9 999 999.99");
        }
    }

    private void checkBankAccountNumber(String bankAccountNumber) throws GenericValidationException {
        if (bankAccountNumber.length() > 8) {
            throw new GenericValidationException("Bank account number must be less than 8 characters");
        }
    }

    private void checkNationalInsuranceNumber(String nationalInsuranceNumber) throws GenericValidationException {
        if (nationalInsuranceNumber.length() > 9) {
            throw new GenericValidationException("National insurance number must be less than 9 characters");
        }
    }

    public void isValidDeliveryEmployeeUpdateRequest(DeliveryEmployeeUpdateRequest deliveryEmployee) throws GenericValidationException {
        checkFirstName(deliveryEmployee.getFirstName());

        checkLastName(deliveryEmployee.getLastName());

        checkSalary(deliveryEmployee.getSalary());

        checkBankAccountNumber(deliveryEmployee.getBankAccountNumber());
    }

    public void isValidDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) throws GenericValidationException {

        checkFirstName(deliveryEmployee.getFirstName());

        checkLastName(deliveryEmployee.getLastName());

        checkSalary(deliveryEmployee.getSalary());

        checkBankAccountNumber(deliveryEmployee.getBankAccountNumber());

        checkNationalInsuranceNumber(deliveryEmployee.getNationalInsuranceNumber());

    }
}
