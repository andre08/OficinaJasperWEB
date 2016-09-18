package com.oficinajasperweb.controller;

import com.oficinajasperweb.dao.ClienteDao;
import com.oficinajasperweb.dao.ConnectionManager;
import com.oficinajasperweb.model.Cliente;
import java.io.File;
import java.util.HashMap;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

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
    @Produces({"application/pdf"})
    @Path("print/{id}/")
    public Response getImpressaoCliente(@PathParam("id") Integer id) throws JRException {

        System.out.println("impressão");
        ClienteDao clienteDao = new ClienteDao();
        HashMap<String, Object> resultado = new HashMap<String, Object>();
        resultado.put("IDCLIENTE", clienteDao.getById(id).getId());

        String origem = "Lista de Clientes.jasper";
        String destino = clienteDao.getById(id).hashCode() + ".pdf";

        JasperRunManager.runReportToPdfFile("/jasper/" + origem, "/jasper/" + destino, resultado, ConnectionManager.getConnection());
        File file = new File("c:/jasper/" + destino);

        ResponseBuilder responseBuilder = Response.ok((Object) file);
        responseBuilder.header("Content-Disposition", "attachment; filename=\"" + destino + "\"");
        return responseBuilder.build();
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

    @GET
    @Produces({"application/pdf"})
    @Path("print/")
    public Response getImpressaoListaCliente() throws JRException {

        System.out.println("impressão");
        HashMap<String, Object> resultado = new HashMap<String, Object>();

        String origem = "Lista de Clientes.jasper";
        String destino = "listacliente.pdf";

        JasperRunManager.runReportToPdfFile("/jasper/" + origem, "/jasper/" + destino, null, ConnectionManager.getConnection());
        File file = new File("c:/jasper/" + destino);

        ResponseBuilder responseBuilder = Response.ok((Object) file);
        responseBuilder.header("Content-Disposition", "attachment; filename=\"" + destino + "\"");
        
        return responseBuilder.build();
    }

}
