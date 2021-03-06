package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.ValidatorResponse;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.ClienteService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.inject.Inject;
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
 * @author xergio
 * @version 1
 */
@Singleton
@Path("clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/clientes", description = "REST Service de Clientes")
public class ClienteRest {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Inject
    private ClienteService service;

    private static final Logger LOG = Logger.getLogger("ClientesRest");

    @GET
    @Secured
    @Path("/find/all")
    @ApiOperation(value = "Obtiene una lista de Clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de clientes");

        return Response.ok().entity(service.getClientes()).build();
    }

    @GET
    @Secured
    @Path("/find/{id}")
    @ApiOperation(value = "Obtiene un registro de Clientes en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "Obteniendo cliente por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getCliente(id)).build();
    }

    @POST
    @Secured
    @Path("/add")
    @ApiOperation(value = "Agrega un registro de Publicacion de Cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addCliente(ClienteDto clienteParam) {
        LOG.log(Level.INFO, "Creando nuevo cliente");
        
        ClienteDto clienteAux;

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddCliente(clienteParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }
            
            // Validacion de codigo cliente unico
            clienteAux = new ClienteDto();
            clienteAux.setCodigoCliente(clienteParam.getCodigoCliente());
            
            if ( !Util.isEmpty(service.getClienteByParam(clienteAux)) ) {
                LOG.log(Level.WARNING, "Error de validacion: Codigo Cliente ya existe.");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Constants.VALIDATION_CLIENTE_CODIGO_CLIENTE_UNIQUE).build();
            }

            clienteParam = service.saveCliente(clienteParam);

            if (Util.isEmpty(clienteParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Cliente {0} creado correctamente.", clienteParam.getId());
            return Response.ok().entity(clienteParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @PUT
    @Secured
    @Path("/update")
    @ApiOperation(value = "Actualiza un registro de Clientes en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateCliente(ClienteDto clienteParam) {
        LOG.log(Level.INFO, "Actualizando cliente por id: {0}", new Object[]{clienteParam.getId()});

        try {

            clienteParam = service.saveCliente(clienteParam);

            LOG.log(Level.INFO, "Cliente {0} actualizado correctamente.", clienteParam.getId());
            return Response.ok().entity(clienteParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @ApiOperation(value = "Borra un registro de Clientes en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteCliente(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "Eliminando cliente por id: {0}", new Object[]{id});

        try {
            service.deleteCliente(id);

            LOG.log(Level.INFO, "Cliente {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
