package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.dto.ValidatorResponse;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.PasswordService;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.AuthService;
import com.minicubic.infoguiahttp.services.UsuarioService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.UUID;
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
 * @author xergio
 * @version 1
 */
@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/usuarios", description = "REST Service de Usuarios")
public class UsuarioRest {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private UsuarioService service;
    
    @Inject
    private AuthService authService;
    
    private static final Logger LOG = Logger.getLogger("UsuariosRest");
    
    @GET
    @Secured
    @Path("/find/all")
    @PermitAll
    @ApiOperation(value = "Obtiene una lista de Usuarios.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de usuarios");
        
        return Response.ok().entity(service.getUsuarios()).build();
    }
 
    @GET
    @Secured
    @Path("/find/{id}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "Obteniendo usuario por id: {0}", new Object[]{id});
        
        return Response.ok().entity(service.getUsuario(id)).build();
    }
    
    @POST
    @Secured
    @Path("/add")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Agrega un registro de Usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addUsuario(UsuarioDto usuarioParam) {
        LOG.log(Level.INFO, "Creando nuevo usuario");
        
        UsuarioDto usuarioAux;

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddUsuario(usuarioParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }
            
            // Validacion de email unico
            usuarioAux = new UsuarioDto();
            usuarioAux.setEmail(usuarioParam.getEmail());
            
            if ( !Util.isEmpty(authService.getUsuarioByParam(usuarioAux)) ) {
                LOG.log(Level.WARNING, "Error de validacion: Email ya existe.");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Constants.VALIDATION_USUARIO_EMAIL_UNIQUE).build();
            }
            
            // Validacion de username unico
            usuarioAux = new UsuarioDto();
            usuarioAux.setUsername(usuarioParam.getUsername());
            
            if ( !Util.isEmpty(authService.getUsuarioByParam(usuarioAux)) ) {
                LOG.log(Level.WARNING, "Error de validacion: Username ya existe.");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Constants.VALIDATION_USUARIO_USERNAME_UNIQUE).build();
            }
            
            // Creamos el usuario
            PasswordService ps = new PasswordService();
            usuarioParam.setPassword(ps.encrypt(usuarioParam.getPassword()));
            usuarioParam.setTokenConfirmacion(UUID.randomUUID().toString());

            usuarioParam = service.saveUsuario(usuarioParam);

            if (Util.isEmpty(usuarioParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Usuario {0} creado correctamente.", usuarioParam.getId());
            return Response.ok().entity(usuarioParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
 
    @PUT
    @Secured
    @Path("/update")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Actualiza un registro de Usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateUsuario(UsuarioDto usuarioParam) {
        LOG.log(Level.INFO, "Actualizando usuario por id: {0}", new Object[]{usuarioParam.getId()});
        
        try {

            usuarioParam = service.saveUsuario(usuarioParam);

            LOG.log(Level.INFO, "Usuario {0} actualizado correctamente.", usuarioParam.getId());
            return Response.ok().entity(usuarioParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
 
    @DELETE
    @Secured
    @Path("/delete/{id}")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Elimina un registro de Usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteUsuario(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "Eliminando usuario por id: {0}", new Object[]{id});
        
        try {
            service.deleteUsuario(id);

            LOG.log(Level.INFO, "Usuario {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
