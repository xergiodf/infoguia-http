package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.dao.TipoHorarioDao;
import com.minicubic.infoguiahttp.dto.TipoHorarioDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.dto.ValidatorResponse;
import com.minicubic.infoguiahttp.model.TipoHorario;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.Validator;
import com.minicubic.infoguiahttp.util.converter.TipoHorarioConverter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
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
 * @author sedf
 */
@Singleton
@Path("tiposHorarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/tiposHorarios", description = "REST Service de Tipos Horarios")
public class TipoHorarioRest {
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Inject
    private TipoHorarioDao dao;

    private static final Logger LOG = Logger.getLogger("TipoHorarioesRest");

    @GET
    @Secured
    @Path("/find/all")
    @ApiOperation(value = "Obtiene una lista de TipoHorarios")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de TipoHorarios");

        try {
            return Response.ok().entity(new TipoHorarioConverter().getTipoHorariosDto(dao.findAll())).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @GET
    @Secured
    @Path("/find/{id}")
    @ApiOperation(value = "Obtiene un registro de TipoHorario en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo TipoHorario por id: {0}", new Object[]{id});

        try {
            return Response.ok().entity(new TipoHorarioConverter().getTipoHorarioDto(dao.find(id))).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @POST
    @Secured
    @Path("/add")
    @ApiOperation(value = "Agrega un registro de Publicacion de TipoHorario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addTipoHorario(TipoHorarioDto tipoHorarioDto) {
        return manageTipoUsuario(tipoHorarioDto, Boolean.TRUE);
    }

    @PUT
    @Secured
    @Path("/update")
    @ApiOperation(value = "Actualiza un registro de TipoHorarios en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateTipoHorario(TipoHorarioDto tipoHorarioDto) {
        return manageTipoUsuario(tipoHorarioDto, Boolean.FALSE);
    }
    
    private Response manageTipoUsuario(TipoHorarioDto tipoHorarioDto, Boolean isNew) {
        LOG.log(Level.INFO, "{0} tipo horario", isNew ? "Registrando" : "Modificando");
        
        try {
            
            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddTipoHorario(tipoHorarioDto);
            
            if ( !validatorResponse.getData() ) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }
            
            // Creamos el cliente
            TipoHorario tipoHorario = new TipoHorarioConverter().getTipoHorario(tipoHorarioDto);
            tipoHorario.setAuditUsuario(usuarioLogueado.getUsername());
            if (isNew)
                dao.create(tipoHorario);
            else  
                tipoHorario = dao.edit(tipoHorario);
            
            LOG.log(Level.INFO, "TipoHorario {0} " + (isNew ? "registado" : "modificado") + " correctamente.", tipoHorario.getId());
            
            return Response.ok().entity(new TipoHorarioConverter().getTipoHorarioDto(tipoHorario)).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @ApiOperation(value = "Borra un registro de TipoHorarios en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteTipoHorario(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando tipoPublicacion por id: {0}", new Object[]{id});

        try {
            dao.remove(dao.find(id));

            LOG.log(Level.INFO, "TipoHorario {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
