package org.kainos.ea.api;

import org.kainos.ea.cli.ProjectDeleteDeliveryEmployeeRequest;
import org.kainos.ea.cli.ProjectDeliveryEmployee;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericDoesNotExistException;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;

public class ProjectService {

    private final ProjectDao projectDao = new ProjectDao();


    public void updateProjectDeliveryEmployee(int projectId, int employeeId, ProjectDeleteDeliveryEmployeeRequest pdeRequest) throws GenericActionFailedException, GenericDoesNotExistException {
        try {
            ProjectDeliveryEmployee projectDeliveryEmployeeToBeUpdated = projectDao.getProjectDeliveryEmployee(projectId, employeeId, pdeRequest.getStartedOnDate());

            if (projectDeliveryEmployeeToBeUpdated==null){
                throw new GenericDoesNotExistException("Delivery Employee does not exist on project with provided start date");
            }

            projectDao.updateProjectDeliveryEmployee(projectId, employeeId, pdeRequest);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new GenericActionFailedException("Update project delivery employee");
        }
    }

}
