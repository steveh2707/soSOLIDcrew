package org.kainos.ea.db;

import org.kainos.ea.cli.ProjectAddDeliveryEmployeesRequest;

import java.sql.*;

public class ProjectDao {

    public void addDeliveryEmployeeToProject(int id, ProjectAddDeliveryEmployeesRequest projectDelivery) throws SQLException {

        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO project_delivery_employee " +
                "(project_id, delivery_employee_id, started_on_date) " +
                "VALUES (?, ?, ?)";

        for (int i = 1; i <= projectDelivery.getDeliveryEmployeeIds().length - 1; i++) {
            insertStatement += ", (?, ?, ?)";
        }

        PreparedStatement st = c.prepareStatement(insertStatement);

        int param = 0;

        for (int j = 0; j <= projectDelivery.getDeliveryEmployeeIds().length-1; j++) {

            st.setInt(param += 1, id);
            st.setInt(param += 1, projectDelivery.getDeliveryEmployeeIds()[j]);
            st.setDate(param += 1, projectDelivery.getStartedOnDate());

        }

        st.executeUpdate();

    }

}
