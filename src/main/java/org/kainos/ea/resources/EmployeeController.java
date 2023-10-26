package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.api.EmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.UserRole;
import org.kainos.ea.client.AuthenticationException;
import org.kainos.ea.cli.DeliveryEmployeeUpdateRequest;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericValidationException;
import org.kainos.ea.client.GenericDoesNotExistException;
import org.kainos.ea.db.AuthDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("So SOLID Crew API")
@Path("/api")
public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeService();
    private final AuthService authService = new AuthService(new AuthDao());

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(@QueryParam("token") String token) {
        try {
            if(!authService.doesTokenHaveRole(token,UserRole.HR)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(employeeService.getAllEmployees())
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/employees/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(DeliveryEmployeeRequest deliveryEmployee, @QueryParam("token") String token) {
        try {
            if(!authService.doesTokenHaveRole(token,UserRole.HR)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            return Response
                    .ok(employeeService.createDeliveryEmployee(deliveryEmployee))
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (GenericValidationException e) {
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

    @GET
    @Path("/employees/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeeById(@PathParam("id") int id,@QueryParam("token") String token) {
        try {
            if(!authService.doesTokenHaveRole(token,UserRole.HR)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(employeeService.getDeliveryEmployeeById(id))
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (GenericDoesNotExistException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/employees/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDeliveryEmployees(@QueryParam("token") String token){
        try {
            if(!authService.doesTokenHaveRole(token,UserRole.HR)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(employeeService.getAllDeliveryEmployees())
                    .build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/employees/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployee(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            if(!authService.doesTokenHaveRole(token,UserRole.HR)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            employeeService.deleteDeliveryEmployee(id);

            return Response.ok().build();

        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (GenericDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/employees/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(@PathParam("id") int id, DeliveryEmployeeUpdateRequest employee,@QueryParam("token") String token) {
        try{
            if(!authService.doesTokenHaveRole(token,UserRole.HR)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            employeeService.updateEmployee(id, employee);

            return Response.ok().build();
        } catch (GenericActionFailedException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();

        } catch (GenericValidationException | GenericDoesNotExistException | AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
