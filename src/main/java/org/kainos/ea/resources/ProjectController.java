package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProjectService;

import javax.ws.rs.Path;

@Api("Runtime Terrors API")
@Path("/api")
public class ProjectController {
    private final ProjectService projectService = new ProjectService();


}
