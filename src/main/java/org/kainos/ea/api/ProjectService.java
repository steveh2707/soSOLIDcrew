package org.kainos.ea.api;

import org.kainos.ea.cli.ProjectDeliveryEmployeeRequest;
import org.kainos.ea.db.ProjectDao;

public class ProjectService {

    private final ProjectDao projectDao = new ProjectDao();

    public int addDeliveryEmployeeToProject(int id, ProjectDeliveryEmployeeRequest projectDelivery) {

        return 1;

    }

}
