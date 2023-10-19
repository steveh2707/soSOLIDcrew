package org.kainos.ea.db;

import org.kainos.ea.cli.ProjectDeliveryEmployee;
import org.kainos.ea.cli.ProjectDeliveryEmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectDao {

    public int addDeliveryEmployeeToProject(int id, ProjectDeliveryEmployeeRequest projectDelivery) throws SQLException {

        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO project_delivery_employee " +
                "(project_id, delivery_employee_id, started_on_date) " +
                "VALUES ";


        for (int deliveryEmployeeId : projectDelivery.getDeliveryEmployeeIds()) {

            insertStatement += "(?, ?, ?)";

            PreparedStatement st = c.prepareStatement(insertStatement);

            st.setInt(1, id);
            st.setInt(2, deliveryEmployeeId);
            st.setDate(3, projectDelivery.getStartedOnDate());

            st.executeUpdate();

            insertStatement += ", ";
        }
        return -1;

    }

}
