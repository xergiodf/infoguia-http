package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.ClientePublicacionDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.ValidatorResponse;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.ClientePublicacionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xergio
 * @version 1
 */
@Singleton
@Path("publicaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/publicaciones", description = "REST Service de Publicaciones")
public class PublicacionRest {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Inject
    private ClientePublicacionService service;

    private static final Logger LOG = Logger.getLogger("ClientePublicacionesRest");

    @GET
    @Secured
    @Path("/find/all")
    @ApiOperation(value = "Obtiene una lista de Publicaciones de Clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de Publicaciones de Clientes");

        return Response.ok().entity(service.getClientePublicaciones()).build();
    }

    @GET
    @Secured
    @Path("/find/{id}")
    @ApiOperation(value = "Obtiene un registro de Publicacion de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo Publicacion de Cliente por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getClientePublicacion(id)).build();
    }
    
    @GET
    @Secured
    @Path("/findByCliente/{id}")
    @ApiOperation(value = "Obtiene un registro de Publicacion de Cliente en base a un ID de Cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByCliente(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "Obteniendo Publicacion de Cliente por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getClientePublicacionesByCliente(id)).build();
    }
    
    @GET
    @Secured
    @Path("/novedades/all")
    @ApiOperation(value = "Obtiene una lista de Novedades")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findNovedades() {
        LOG.log(Level.INFO, "Obteniendo lista de Novedades");

        return Response.ok().entity(service.getNovedades()).build();
    }
    
    @GET
    @Secured
    @Path("/promociones/all")
    @ApiOperation(value = "Obtiene una lista de Promociones")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findPromociones() {
        LOG.log(Level.INFO, "Obteniendo lista de Promociones");

        return Response.ok().entity(service.getPromociones()).build();
    }


    @POST
    @Secured
    @Path("/add")
    @ApiOperation(value = "Agrega un registro de Publicacion de Cliente en base a un ID. En el parametro debe venir especificado el tipo de publicacion (Novedad o Promocion.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addClientePublicacion(ClientePublicacionDto clientePublicacionParam) {
        LOG.log(Level.INFO, "Creando nueva Publicacion de Ciente");

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddClientePublicacion(clientePublicacionParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }

            clientePublicacionParam = service.saveClientePublicacion(clientePublicacionParam);

            if (Util.isEmpty(clientePublicacionParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Publicacion de Cliente {0} creado correctamente.", clientePublicacionParam.getId());
            return Response.ok().entity(clientePublicacionParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @PUT
    @Secured
    @Path("/update")
    @ApiOperation(value = "Actualiza un registro de Publicacion de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateClientePublicacion(ClientePublicacionDto clientePublicacionParam) {
        LOG.log(Level.INFO, "Actualizando Publicacion de Cliente por id: {0}", new Object[]{clientePublicacionParam.getId()});

        try {

            clientePublicacionParam = service.saveClientePublicacion(clientePublicacionParam);

            LOG.log(Level.INFO, "Publicacion de Cliente {0} actualizado correctamente.", clientePublicacionParam.getId());
            return Response.ok().entity(clientePublicacionParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @ApiOperation(value = "Borra un registro de Publicacion de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteClientePublicacion(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando Publicacion de Cliente por id: {0}", new Object[]{id});

        try {
            service.deleteClientePublicacion(id);

            LOG.log(Level.INFO, "Publicacion de Cliente {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
