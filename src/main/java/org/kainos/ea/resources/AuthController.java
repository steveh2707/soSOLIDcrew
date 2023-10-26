package org.kainos.ea.resources;

import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.Credential;
import org.kainos.ea.client.AuthenticationException;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.db.AuthDao;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("So SOLID crew Auth API")
@Path("/api")
public class AuthController {
    private AuthService authService = new AuthService(new AuthDao());

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credential login){
        try {
            String token = authService.login(login);
            if(token == null){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.ok(token).build();
        } catch(AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch(GenericActionFailedException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Credential login) {
        try {
            return Response.ok(authService.register(login)).build();
        } catch(AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch(GenericActionFailedException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
