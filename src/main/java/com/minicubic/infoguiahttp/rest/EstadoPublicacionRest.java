package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.EstadoPublicacionDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.ValidatorResponse;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.EstadoPublicacionService;
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
 * @version 1 - 27.04.2017
 */
@Singleton
@Path("estadosPublicaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/estadosPublicaciones", description = "REST Service de Estados de Publicaciones")
public class EstadoPublicacionRest {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Inject
    private EstadoPublicacionService service;

    private static final Logger LOG = Logger.getLogger("EstadoPublicacionsRest");

    @GET
    @Secured
    @Path("/find/all")
    @ApiOperation(value = "Obtiene una lista de EstadoPublicacions")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de estadoPublicaciones");

        return Response.ok().entity(service.getEstadoPublicaciones()).build();
    }

    @GET
    @Secured
    @Path("/find/{id}")
    @ApiOperation(value = "Obtiene un registro de EstadoPublicacions en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo estadoPublicacion por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getEstadoPublicacion(id)).build();
    }

    @POST
    @Secured
    @Path("/add")
    @ApiOperation(value = "Agrega un registro de Publicacion de EstadoPublicacion")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addEstadoPublicacion(EstadoPublicacionDto estadoPublicacionParam) {
        LOG.log(Level.INFO, "Creando nuevo estadoPublicacion");

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddEstadoPublicacion(estadoPublicacionParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }

            estadoPublicacionParam = service.saveEstadoPublicacion(estadoPublicacionParam);

            if (Util.isEmpty(estadoPublicacionParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "EstadoPublicacion {0} creado correctamente.", estadoPublicacionParam.getId());
            return Response.ok().entity(estadoPublicacionParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @PUT
    @Secured
    @Path("/update")
    @ApiOperation(value = "Actualiza un registro de EstadoPublicacions en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateEstadoPublicacion(EstadoPublicacionDto estadoPublicacionParam) {
        LOG.log(Level.INFO, "Actualizando estadoPublicacion por id: {0}", new Object[]{estadoPublicacionParam.getId()});

        try {

            estadoPublicacionParam = service.saveEstadoPublicacion(estadoPublicacionParam);

            LOG.log(Level.INFO, "EstadoPublicacion {0} actualizado correctamente.", estadoPublicacionParam.getId());
            return Response.ok().entity(estadoPublicacionParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @ApiOperation(value = "Borra un registro de EstadoPublicacions en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteEstadoPublicacion(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando estadoPublicacion por id: {0}", new Object[]{id});

        try {
            service.deleteEstadoPublicacion(id);

            LOG.log(Level.INFO, "EstadoPublicacion {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
