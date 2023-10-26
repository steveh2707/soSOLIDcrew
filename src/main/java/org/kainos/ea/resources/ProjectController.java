package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.api.ProjectService;
import org.kainos.ea.cli.ProjectAddDeliveryEmployeesRequest;
import org.kainos.ea.cli.UserRole;
import org.kainos.ea.client.*;
import org.kainos.ea.cli.ProjectDeleteDeliveryEmployeeRequest;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericDoesNotExistException;
import org.kainos.ea.db.AuthDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("So SOLID Crew API")
@Path("/api")
public class ProjectController {
    private final ProjectService projectService = new ProjectService();
    private final AuthService authService = new AuthService(new AuthDao());

    @POST
    @Path("/projects/{id}/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDeliveryEmployeeToProject(@PathParam("id") int id, ProjectAddDeliveryEmployeesRequest projectDelivery,
                                                 @QueryParam("token") String token) {

        try {
            UserRole role = authService.getTokenRole(token);
            if(role != UserRole.MANAGEMENT){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            projectService.addDeliveryEmployeeToProject(id, projectDelivery);
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (GenericDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/projects/{project_id}/delivery/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectDeliveryEmployee(@PathParam("project_id") int projectId,
                                @PathParam("employee_id") int employeeId,
                                ProjectDeleteDeliveryEmployeeRequest pdeRequest,
                                                  @QueryParam("token") String token) {

        try {
            if(!authService.doesTokenHaveRole(token,UserRole.MANAGEMENT)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            projectService.updateProjectDeliveryEmployee(projectId,employeeId,pdeRequest);
            return Response
                    .ok()
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (GenericDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
