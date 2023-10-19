package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.Employee;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericValidationException;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.EmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final DeliveryEmployeeValidator deliveryEmployeeValidator = new DeliveryEmployeeValidator();

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
}
