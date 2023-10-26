package org.kainos.ea.api;

import org.kainos.ea.cli.*;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericValidationException;
import org.kainos.ea.core.EmployeeValidator;
import org.kainos.ea.client.GenericDoesNotExistException;
import org.kainos.ea.db.EmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDao();
    private final EmployeeValidator deliveryEmployeeValidator = new EmployeeValidator();

    public List<Employee> getAllEmployees() throws GenericActionFailedException {

        try {
            return employeeDao.getAllEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GenericActionFailedException("get books");
        }
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) throws GenericActionFailedException, GenericValidationException {
        try {
            deliveryEmployeeValidator.isValidDeliveryEmployee(deliveryEmployee);

            int id = employeeDao.createDeliveryEmployee(deliveryEmployee);

            if (id == -1) {
                throw new GenericActionFailedException("create delivery employee");
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GenericActionFailedException("create delivery employee");
        }
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws GenericActionFailedException, GenericDoesNotExistException {

        try {
            DeliveryEmployee deliveryEmployee = employeeDao.getDeliveryEmployeeById(id);

            if (deliveryEmployee == null) {
                throw new GenericDoesNotExistException("Delivery Employee does not exist");
            }

            return deliveryEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GenericActionFailedException("Get Delivery Employee By ID");
        }
    }


    public List<DeliveryEmployee> getAllDeliveryEmployees() throws GenericActionFailedException {

        try {

            return employeeDao.getAllDeliveryEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GenericActionFailedException("get all delivery employees");

        }
    }



    public void updateEmployee(int id, DeliveryEmployeeUpdateRequest employee) throws GenericDoesNotExistException, GenericValidationException, GenericActionFailedException {
        try {
            deliveryEmployeeValidator.isValidDeliveryEmployeeUpdateRequest(employee);

            Employee employeeToUpdate = employeeDao.getDeliveryEmployeeById(id);

            if (employeeToUpdate == null) {
                throw new GenericDoesNotExistException("Delivery employee does not exist");
            }

            employeeDao.updateDeliveryEmployee(id, employee);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GenericActionFailedException("Failed to update delivery employee");
        }
    }


    public void deleteDeliveryEmployee(int id) throws GenericDoesNotExistException, GenericActionFailedException {
        try {
            DeliveryEmployee deliveryEmployeeToDelete = employeeDao.getDeliveryEmployeeById(id);

            if (deliveryEmployeeToDelete == null) {
                throw new GenericDoesNotExistException("delivery employee does not exist");
            }

            employeeDao.deleteDeliveryEmployee(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new GenericActionFailedException("failed to delete delivery employee");
        }
    }


}
