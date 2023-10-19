package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.EmployeeService;
import org.kainos.ea.cli.ProjectDeliveryRequest;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericDoesNotExistException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Runtime Terrors API")
@Path("/api")
public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeService();

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(employeeService.getAllEmployees())
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/employees/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeeById(@PathParam("id") int id) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(employeeService.getDeliveryEmployeeById(id))
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (GenericDoesNotExistException e){

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/employees/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDeliveryEmployees(){
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(employeeService.getAllDeliveryEmployees())
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/projects/{id}/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDeliveryEmployeeToProject(@PathParam("id") int id, ProjectDeliveryRequest projectDelivery){
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(employeeService.addDeliveryEmployeeToProject(id, projectDelivery))
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (GenericDoesNotExistException e){

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
