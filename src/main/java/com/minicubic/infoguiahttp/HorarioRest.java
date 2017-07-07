package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.dao.SucursalHorarioCabDao;
import com.minicubic.infoguiahttp.dao.SucursalHorarioDetDao;
import com.minicubic.infoguiahttp.dto.SucursalHorarioCabDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.dto.ValidatorResponse;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Validator;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
@Path("horarios")
@Api(value = "/horarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class HorarioRest {
    
    private static final Logger LOG = Logger.getLogger("HorarioRest");
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private SucursalHorarioCabDao sucursalHorarioCabDao;
    
    @Inject
    private SucursalHorarioDetDao sucursalHorarioDetDao;
    
    /* CABECERA */
    @POST
    @Secured
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Agrega un nuevo horario cab.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response createHorario(SucursalHorarioCabDto horarioParam) {
        return manageHorario(horarioParam, Boolean.TRUE);
    }

    @PUT
    @Secured
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Modifica un horario cab.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response editHorario(SucursalHorarioCabDto horarioParam) {
        return manageHorario(horarioParam, Boolean.FALSE);
    }
    
    private Response manageHorario(SucursalHorarioCabDto evaluacionParam, Boolean isNew) {
        LOG.log(Level.INFO, "{0} horario", isNew ? "Registrando" : "Modificando");
        
        try {
            
            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddHorarioCab(evaluacionParam);
            
            if ( !validatorResponse.getData() ) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }
            
            // Creamos el evaluacion
            EvaluacionCab evaluacion = EvaluacionCabConverter.getInstance().getEvaluacionCab(evaluacionParam);
            evaluacion.setAuditUsuario(usuarioLogueado.getUsername());
            if (isNew)
                evaluacionCabDao.create(evaluacion);
            else  
                evaluacion = evaluacionCabDao.edit(evaluacion);
            
            LOG.log(Level.INFO, "Evaluacion {0} " + (isNew ? "registado" : "modificado") + " correctamente.", evaluacion.getId());
            
            return Response.ok().entity(EvaluacionCabConverter.getInstance().getEvaluacionCabDto(evaluacion)).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ValidatorMessages.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @Path("{id}")
    @ApiOperation(value = "Elimina un evaluacion.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response remove(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando Evaluacion por ID {0}", id);
        
        try {
            
//            evaluacionCabDao.remove(evaluacionCabDao.find(id));
            evaluacionCabDao.delete(id);
            
            LOG.log(Level.INFO, "Evaluacion borrado correctamente");
            
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ValidatorMessages.MSG_ERROR_DEFAULT).build();
        }
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "Obtiene un evaluacion.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findEvaluacion(@PathParam("id") Integer id) {
        try {
            return Response.ok(EvaluacionCabConverter.getInstance().getEvaluacionCabDto(evaluacionCabDao.find(id))).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ValidatorMessages.MSG_ERROR_DEFAULT).build();
        }
    }

    @GET
    @ApiOperation(value = "Obtiene una lista de evaluacions.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findEvaluacions() {
        try {
            return Response.ok(EvaluacionCabConverter.getInstance().getEvaluacionCabsDto(evaluacionCabDao.findAll())).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ValidatorMessages.MSG_ERROR_DEFAULT).build();
        }
    }

    @GET
    @Path("{from}/{to}")
    @ApiOperation(value = "Obtiene una lista de evaluacions por rango.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findRangeEvaluacion(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return Response.ok(EvaluacionCabConverter.getInstance().getEvaluacionCabsDto(evaluacionCabDao.findRange(new int[]{from, to}))).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ValidatorMessages.MSG_ERROR_DEFAULT).build();
        }
    }
}
