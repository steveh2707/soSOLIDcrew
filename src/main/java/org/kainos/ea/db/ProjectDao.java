package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.ProjectDeleteDeliveryEmployeeRequest;
import org.kainos.ea.cli.ProjectDeliveryEmployee;

import java.sql.*;

public class ProjectDao {

    public ProjectDeliveryEmployee getProjectDeliveryEmployee(int projectId, int employeeId, Date startedOnDate) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String updateStatement = "SELECT project_id, delivery_employee_id, started_on_date, ended_on_date FROM project_delivery_employee WHERE project_id = ? AND delivery_employee_id = ? AND started_on_date = ?;";
        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, projectId);
        st.setInt(2, employeeId);
        st.setDate(3, startedOnDate);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            return new ProjectDeliveryEmployee(
                    rs.getInt("project_id"),
                    rs.getInt("delivery_employee_id"),
                    rs.getDate("started_on_date"),
                    rs.getDate("ended_on_date")
            );
        }
        return null;
    }

    public void updateProjectDeliveryEmployee(int projectId, int employeeId, ProjectDeleteDeliveryEmployeeRequest pdeRequest) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String updateStatement = "UPDATE project_delivery_employee SET ended_on_date = ? WHERE project_id = ? AND delivery_employee_id = ? AND started_on_date = ?;";
        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setDate(1, pdeRequest.getEndedOnDate());
        st.setInt(2, projectId);
        st.setInt(3, employeeId);
        st.setDate(4, pdeRequest.getStartedOnDate());

        st.executeUpdate();

    }

}
