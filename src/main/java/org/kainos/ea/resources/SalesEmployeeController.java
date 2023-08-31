package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.SalesEmployeeService;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.client.FailedToCreateSalesEmployeeException;
import org.kainos.ea.client.InvalidSalesEmployeeException;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("FunkBot API")
@Path("/api")
public class SalesEmployeeController {
    private SalesEmployeeService service = new SalesEmployeeService();

    @POST
    @Path("/salesEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(SalesEmployee salesEmployee){
        try {
            return Response.ok(service.createSalesEmployee(salesEmployee)).build();
        } catch (FailedToCreateSalesEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (InvalidSalesEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
