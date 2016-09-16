package com.oficinajasperweb.controller;

import com.oficinajasperweb.dao.ClienteDao;
import com.oficinajasperweb.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author andre ulisses
 */
@Path("cliente")
public class ClienteController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Cliente> listClientes() {

        ClienteDao clienteDao = new ClienteDao();
        return clienteDao.getAll();
        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Cliente getClientes(@PathParam("id") Integer id) {

        System.out.println("getClientes");
        ClienteDao clienteDao = new ClienteDao();
        return clienteDao.getById(id);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("print/{id}/")
    public String getImpressaoCliente(@PathParam("id") Integer id) {
        System.out.println("getImpressaoCliente");
        ClienteDao clienteDao = new ClienteDao();
        return "" + clienteDao.getById(id).hashCode() + ".pdf";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response createCliente(Cliente cliente) {
        
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.save(cliente);
        
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response updateCliente(Cliente cliente) {
        
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.save(cliente);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Response deleteCliente(@PathParam("id") Integer id) {

        ClienteDao clienteDao = new ClienteDao();
        clienteDao.delete(clienteDao.getById(id));
        
        return Response.status(Response.Status.OK).build();
    }

}
