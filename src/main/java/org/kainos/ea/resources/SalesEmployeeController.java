package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.SalesEmployeeService;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.FailedToCreateSalesEmployeeException;
import org.kainos.ea.client.FailedToGetSalesEmployeeException;
import org.kainos.ea.client.FailedToUpdateSalesEmployeeException;
import org.kainos.ea.client.InvalidSalesEmployeeException;
import org.kainos.ea.client.SalesEmployeeDoesNotExistException;

import javax.ws.rs.GET;
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

    @GET
    @Path("/salesEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployees(){
        try {
            return Response.ok(service.getAllSalesEmployees()).build();
        } catch (FailedToGetSalesEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/salesEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeeById(@PathParam("id") int id){
        try {
            return Response.ok(service.getSalesEmployeeByID(id)).build();
        } catch (FailedToGetSalesEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (SalesEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/salesEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(SalesEmployeeRequest salesEmployee){
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

    @PUT
    @Path("/salesEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSalesEmployee(@PathParam("id") int id, SalesEmployeeRequest salesEmployee){
        try {
            return Response.ok(service.updateSalesEmployee(id, salesEmployee)).build();
        } catch (InvalidSalesEmployeeException | SalesEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateSalesEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
