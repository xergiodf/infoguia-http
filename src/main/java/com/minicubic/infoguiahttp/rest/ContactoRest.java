package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.SucursalContactoDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.ValidatorResponse;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.SucursalContactoService;
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
@Path("contactos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/contactos", description = "REST Service de Contactos")
public class ContactoRest {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Inject
    private SucursalContactoService service;

    private static final Logger LOG = Logger.getLogger("SucursalContactoesRest");

    @GET
    @Secured
    @Path("/find/all")
    @ApiOperation(value = "Obtiene una lista de Contactos de Clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de Contactos de Clientes");

        return Response.ok().entity(service.getSucursalContactoes()).build();
    }

    @GET
    @Secured
    @Path("/find/{id}")
    @ApiOperation(value = "Obtiene un registro de Contacto de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo Contacto de Cliente por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getSucursalContacto(id)).build();
    }

    @POST
    @Secured
    @Path("/add")
    @ApiOperation(value = "Agrega un registro de Contacto de Cliente en base a un ID. En el parametro debe venir especificado el tipo de publicacion (Novedad o Promocion.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addSucursalContacto(SucursalContactoDto sucursalContactoParam) {
        LOG.log(Level.INFO, "Creando nueva Contacto de Ciente");

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddSucursalContacto(sucursalContactoParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }

            sucursalContactoParam = service.saveSucursalContacto(sucursalContactoParam);

            if (Util.isEmpty(sucursalContactoParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Contacto de Cliente {0} creado correctamente.", sucursalContactoParam.getId());
            return Response.ok().entity(sucursalContactoParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @PUT
    @Secured
    @Path("/update")
    @ApiOperation(value = "Actualiza un registro de Contacto de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateSucursalContacto(SucursalContactoDto sucursalContactoParam) {
        LOG.log(Level.INFO, "Actualizando Contacto de Cliente por id: {0}", new Object[]{sucursalContactoParam.getId()});

        try {

            sucursalContactoParam = service.saveSucursalContacto(sucursalContactoParam);

            LOG.log(Level.INFO, "Contacto de Cliente {0} actualizado correctamente.", sucursalContactoParam.getId());
            return Response.ok().entity(sucursalContactoParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @ApiOperation(value = "Borra un registro de Contacto de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteSucursalContacto(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando Contacto de Cliente por id: {0}", new Object[]{id});

        try {
            service.deleteSucursalContacto(id);

            LOG.log(Level.INFO, "Contacto de Cliente {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
