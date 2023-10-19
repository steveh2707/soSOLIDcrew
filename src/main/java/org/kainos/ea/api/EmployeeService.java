package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.Employee;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.db.EmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDao();

    public List<Employee> getAllEmployees() throws GenericActionFailedException {

        try {
//            List<Book> bookList = bookDao.getAllBooks();
//            return bookList;
            return employeeDao.getAllEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GenericActionFailedException("get all employees");
        }
    }

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws  GenericActionFailedException {

        try{

            return employeeDao.getAllDeliveryEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
           throw new GenericActionFailedException("get all delivery employees");

            }
        }

}
