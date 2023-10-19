package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.Employee;
import org.kainos.ea.cli.ProjectDeliveryRequest;

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

    public int addDeliveryEmployeeToProject(int id, ProjectDeliveryRequest projectDelivery) throws SQLException {

        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO project_delivery_employee " +
                "(project_id, delivery_employee_id, started_on_date) " +
                "VALUES (?, ?, ?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, id);
        st.setInt(2, projectDelivery.getDeliveryEmployeeId());
        st.setDate(3, projectDelivery.getStartedOnDate());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if(rs.next()){
            return rs.getInt(1);
        }

        return -1;

    }
}
