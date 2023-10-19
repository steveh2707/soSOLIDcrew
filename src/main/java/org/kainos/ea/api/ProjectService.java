package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.ProjectAddDeliveryEmployeesRequest;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericDoesNotExistException;
import org.kainos.ea.db.EmployeeDao;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;

public class ProjectService {

    private final ProjectDao projectDao = new ProjectDao();
    private final EmployeeDao employeeDao = new EmployeeDao();

    public void addDeliveryEmployeeToProject(int id, ProjectAddDeliveryEmployeesRequest projectDelivery) throws GenericActionFailedException, GenericDoesNotExistException {

        try{
            for (int employeeID : projectDelivery.getDeliveryEmployeeIds()) {
                DeliveryEmployee de = employeeDao.getDeliveryEmployeeById(employeeID);
                if (de == null) {
                    throw new GenericDoesNotExistException("delivery employee does not exist");
                }
            }

            projectDao.addDeliveryEmployeeToProject(id, projectDelivery);

        } catch (SQLException e) {
            throw new GenericActionFailedException("Add Delivery Employee to Project");
        }

    }

}
