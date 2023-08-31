package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("FunkBot API")
@Path("/api")
public class DeliveryEmployeeController {
    private final DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();

    @GET
    @Path("/deliveryEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployees() {
        try {
            return Response.ok(deliveryEmployeeService.getAllDeliveryEmployees()).build();
        } catch (FailedToGetDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/deliveryEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeesByID(@PathParam("id") int id) {
        try {
            return Response.ok(deliveryEmployeeService.getDeliveryEmployeesByID(id)).build();
        } catch (FailedToGetDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (DeliveryEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/deliveryEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployees(DeliveryEmployeeRequest deliveryEmployee) {
        try {
            return Response.ok(deliveryEmployeeService.createDeliveryEmployee(deliveryEmployee)).build();
        } catch (FailedToCreateDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/deliveryEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeliveryEmployees(@PathParam("id") int id, DeliveryEmployeeRequest deliveryEmployee) {
        try {
            deliveryEmployeeService.updateDeliveryEmployee(id, deliveryEmployee);

            return Response.ok().build();
        } catch (InvalidDeliveryEmployeeException | DeliveryEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/deliveryEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployees(@PathParam("id") int id) {
        try {
            deliveryEmployeeService.deleteDeliveryEmployee(id);

            return Response.ok().build();
        } catch (DeliveryEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}