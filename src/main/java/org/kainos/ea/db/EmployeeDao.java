package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public List<Employee> getAllEmployees() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        assert c != null;

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT employee_id, first_name, last_name, salary, bank_account_number, national_insurance_number FROM employee;");

        List<Employee> employeeList = new ArrayList<>();

        while (rs.next()) {
            Employee employee = new DeliveryEmployee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account_number"),
                    rs.getString("national_insurance_number")
            );

            employeeList.add(employee);
        }

        return employeeList;
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        assert c != null;

        String insertStatement = "SELECT employee_id, first_name, " +
                "last_name, salary, bank_account_number, national_insurance_number " +
                "FROM delivery_employee " +
                "INNER JOIN employee " +
                "USING(employee_id) " +
                "WHERE employee_id = ?;";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            return new DeliveryEmployee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account_number"),
                    rs.getString("national_insurance_number")
            );

        }

        return null;
    }


    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        assert c != null;

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("Select employee_id, first_name, last_name, salary, bank_account_number, national_insurance_number " +
                "from delivery_employee LEFT JOIN employee USING (employee_id)");

        List<DeliveryEmployee> deliveryEmployeeList = new ArrayList<>();

        while (rs.next()) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account_number"),
                    rs.getString("national_insurance_number")
            );

            deliveryEmployeeList.add(deliveryEmployee);

        }
        return deliveryEmployeeList;
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        // Insert employee into employee table
        String insertEmployeeStatement = "INSERT INTO employee (first_name, last_name, salary, bank_account_number, national_insurance_number) VALUES (?,?,?,?,?);";
        PreparedStatement ste = c.prepareStatement(insertEmployeeStatement, Statement.RETURN_GENERATED_KEYS);

        ste.setString(1, deliveryEmployee.getFirstName());
        ste.setString(2, deliveryEmployee.getLastName());
        ste.setDouble(3, deliveryEmployee.getSalary());
        ste.setString(4, deliveryEmployee.getBankAccountNumber());
        ste.setString(5, deliveryEmployee.getNationalInsuranceNumber());

        ste.executeUpdate();
        ResultSet rse = ste.getGeneratedKeys();

        if (rse.next()) {
            int employeeId = rse.getInt(1);

            // insert new employee id into delivery employee table
            String insertDeliveryEmployeeStatement = "INSERT INTO delivery_employee (employee_id) VALUES (?);";
            PreparedStatement stde = c.prepareStatement(insertDeliveryEmployeeStatement, Statement.RETURN_GENERATED_KEYS);

            stde.setInt(1, employeeId);
            stde.executeUpdate();
            ResultSet rsde = stde.getGeneratedKeys();

            if (rsde.next()) {
                // return employee id
                return employeeId;
            }

            return -1;
        }

        return -1;
    }
}
