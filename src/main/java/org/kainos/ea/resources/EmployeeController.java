package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.EmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.client.GenericValidationException;
import org.kainos.ea.client.GenericDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @POST
    @Path("/employees/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(DeliveryEmployeeRequest deliveryEmployee) {
        try {
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


}
