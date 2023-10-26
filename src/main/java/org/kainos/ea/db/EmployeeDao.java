package org.kainos.ea.db;

import org.kainos.ea.cli.*;
import org.kainos.ea.client.GenericDoesNotExistException;

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


  
    public void updateDeliveryEmployee(int id, DeliveryEmployeeUpdateRequest employee) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String updateStatement = "UPDATE employee SET first_name = ?, last_name = ?, salary = ?, bank_account_number = ? WHERE employee_id = ?";

      
        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, employee.getFirstName());
        st.setString(2, employee.getLastName());
        st.setDouble(3, employee.getSalary());
        st.setString(4,employee.getBankAccountNumber());
        st.setInt(5,id);

        st.executeUpdate();
    }


    public void updateProjectDeliveryEmployee(ProjectDeliveryEmployee projectDeliveryEmployee) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String updateStatement = "UPDATE project_delivery_employee SET ended_on_date = ? WHERE project_id = ? AND delivery_employee_id = ? AND started_on_date = ?;";
        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setDate(1, projectDeliveryEmployee.getEndedOnDate());
        st.setInt(2, projectDeliveryEmployee.getProjectId());
        st.setInt(3, projectDeliveryEmployee.getDeliveryEmployeeId());
        st.setDate(4, projectDeliveryEmployee.getStartedOnDate());


        st.executeUpdate();
    }

    public void deleteDeliveryEmployee(int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        assert c != null;

        String updateStatement = "DELETE delivery_employee, employee " +
                "FROM delivery_employee " +
                "LEFT JOIN employee USING (employee_id) " +
                "WHERE employee_id = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, id);

        st.executeUpdate();
    }

}
