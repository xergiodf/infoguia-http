package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.dto.EstadoUsuarioDto;
import com.minicubic.infoguiahttp.dto.TipoUsuarioDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.dto.ValidatorResponse;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.MailService;
import com.minicubic.infoguiahttp.util.PasswordService;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.AuthService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xergio
 * @version 3 - 07/04/2017
 */

@Singleton
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/auth", description = "REST Service de Autenticacion")
public class AuthenticationRest {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private AuthService service;
    
    private static final Logger LOG = Logger.getLogger("AuthenticationRest");
    
    @POST
    @Path("admin/login")
    @ApiOperation(value = "Valida las credenciales del usuario y devuelve un token de autorizacion. El usuario debe estar con estado ACTIVO.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "No coinciden Usuario/Contrase\u00f1a"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response loginUsuarioAdmin(UsuarioDto usuarioParam) {
        usuarioParam.setAdmin(Boolean.TRUE);
        return loginUsuario(usuarioParam);
    }
    
    @POST
    @Path("public/login")
    @ApiOperation(value = "Valida las credenciales del usuario y devuelve un token de autorizacion. El usuario debe estar con estado ACTIVO.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "No coinciden Usuario/Contrase\u00f1a"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    private  Response loginUsuario(UsuarioDto usuarioParam) {
        LOG.log(Level.INFO, "Obteniendo usuario: {0}", usuarioParam.getUsername());
      
        try {
            
            PasswordService ps = new PasswordService();
            String encryptedPassword = ps.encrypt(usuarioParam.getPassword());

            UsuarioDto usuarioDto = service.getUsuarioByCredentials(usuarioParam.getUsername(), encryptedPassword);
            
            if (Util.isEmpty(usuarioDto)) {
                LOG.log(Level.WARNING, "No coinciden usuario/contrase\u00f1a -> {0}:{1}", new Object[]{usuarioParam.getUsername(), encryptedPassword});
                return Response.status(Response.Status.NOT_FOUND).entity("No coinciden usuario/contraseña").build();
            }
            
            // Verificamos si es admin
            if (!Util.isEmpty(usuarioParam.getAdmin()) &&
                    usuarioParam.getAdmin().equals(Boolean.TRUE)) {
                
                
            }
            
            // Generamos el token de autorizacion
            usuarioDto.setTokenAuth(Util.createToken(usuarioDto.getId()));

            LOG.log(Level.INFO, "Login satisfactorio: {0}", new Object[]{usuarioParam.getUsername()});
            return Response.ok().entity(usuarioDto).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en " + this.getClass().toGenericString()).build();
        }
    }
    
    @POST
    @Path("public/signup")
    @ApiOperation(value = "Registra un nuevo usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response registrarUsuario(UsuarioDto usuarioParam) {
        LOG.log(Level.INFO, "Registando nuevo usuario");
        
        UsuarioDto usuarioAux;

        try {
            
            // Asumimos que todos los usuarios registrados mediante este metodo
            // son usuarios nuevos de tipo usuario (no admin)
            usuarioParam.setTipoUsuarioDto(new TipoUsuarioDto(new Integer(Constants.DB_USR_TIPO_USUARIO_ID)));
            usuarioParam.setEstadoUsuarioDto(new EstadoUsuarioDto(Constants.DB_USR_ESTADO_FALTA_ACTIVAR_ID));
            
            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddUsuario(usuarioParam);
            if ( !validatorResponse.getData() ) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }
            
            // Validacion de email unico
            usuarioAux = new UsuarioDto();
            usuarioAux.setEmail(usuarioParam.getEmail());
            
            if ( !Util.isEmpty(service.getUsuarioByParam(usuarioAux)) ) {
                LOG.log(Level.WARNING, "Error de validacion: Email ya existe.");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Constants.VALIDATION_USUARIO_EMAIL_UNIQUE).build();
            }
            
            // Validacion de username unico
            usuarioAux = new UsuarioDto();
            usuarioAux.setUsername(usuarioParam.getUsername());
            
            if ( !Util.isEmpty(service.getUsuarioByParam(usuarioAux)) ) {
                LOG.log(Level.WARNING, "Error de validacion: Username ya existe.");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Constants.VALIDATION_USUARIO_USERNAME_UNIQUE).build();
            }
            
            // Creamos el usuario
            PasswordService ps = new PasswordService();
            usuarioParam.setPassword(ps.encrypt(usuarioParam.getPassword()));
            usuarioParam.setTokenConfirmacion(UUID.randomUUID().toString());
            
            usuarioParam = service.saveUsuario(usuarioParam);
            
            if ( Util.isEmpty(usuarioParam) ) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Usuario {0} creado correctamente.", usuarioParam.getId());
            
            // Enviamos el mail
            MailService.enviarMailActivacion(usuarioParam);            
            
            return Response.ok().entity(usuarioParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    @POST
    @Path("social/signup")
    @ApiOperation(value = "Registra un nuevo usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response registrarUsuarioRedSocial(UsuarioDto usuarioParam) {
        LOG.log(Level.INFO, "Registando nuevo usuario");
        
        UsuarioDto usuarioAux;

        try {
            
            // Asumimos que todos los usuarios registrados mediante este metodo
            // son usuarios nuevos de tipo usuario (no admin)
            usuarioParam.setTipoUsuarioDto(new TipoUsuarioDto(new Integer(Constants.DB_USR_TIPO_USUARIO_ID)));
            usuarioParam.setEstadoUsuarioDto(new EstadoUsuarioDto(Constants.DB_USR_ESTADO_ACTIVO_ID));
            
            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddUsuarioSocial(usuarioParam);
            if ( !validatorResponse.getData() ) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }
            
            // Generamos una contrasenha en base al username (MEJORAR)
            // Creamos el usuario
            PasswordService ps = new PasswordService();
            usuarioParam.setPassword(ps.encrypt(usuarioParam.getUsername() + ".123*"));
            
            // Validacion de email unico
            usuarioAux = new UsuarioDto();
            usuarioAux.setEmail(usuarioParam.getEmail());
            
            if ( !Util.isEmpty(service.getUsuarioByParam(usuarioAux)) ) {
                LOG.log(Level.WARNING, "Error de validacion: Email ya existe.");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Constants.VALIDATION_USUARIO_EMAIL_UNIQUE).build();
            }
            
            // Validacion de username unico
            usuarioAux = new UsuarioDto();
            usuarioAux.setUsername(usuarioParam.getUsername());
            
            if ( !Util.isEmpty(service.getUsuarioByParam(usuarioAux)) ) {
                LOG.log(Level.WARNING, "Error de validacion: Username ya existe.");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Constants.VALIDATION_USUARIO_USERNAME_UNIQUE).build();
            }
            
            usuarioParam = service.saveUsuario(usuarioParam);
            
            if ( Util.isEmpty(usuarioParam) ) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Usuario Social {0} creado correctamente.", usuarioParam.getId());
            
            // Enviamos el mail
            MailService.enviarMailActivacion(usuarioParam);            
            
            return Response.ok().entity(usuarioParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    @GET
    @Path("public/confirm/{username}/{tokenConfirmacion}")
    @ApiOperation(value = "Registra un nuevo usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Usuario no encontrado"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response confirmEmail(@PathParam(value = "username") String username, 
            @PathParam(value = "tokenConfirmacion") String tokenConfirmacion) {
        LOG.log(Level.INFO, "Confirmando email del usuario {0}", username);
        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsername(username);
        usuarioDto.setTokenConfirmacion(tokenConfirmacion);
        
        try {
            
            // Buscamos un registro de usuario en base al username y el token
            usuarioDto = service.getUsuarioByParam(usuarioDto);
            
            if ( Util.isEmpty(usuarioDto) ) {
                LOG.log(Level.WARNING, "Usuario no encontrado en base a username y tokenConfirmacion");
                return Response.status(Response.Status.NOT_FOUND).entity(Constants.VALIDATION_USUARIO_NOT_FOUND).build();
            }
            
            // Activamos al usuario
            usuarioDto.setEstadoUsuarioDto(new EstadoUsuarioDto(Constants.DB_USR_ESTADO_ACTIVO_ID));
            usuarioDto.setTokenConfirmacion(null);
            
            usuarioDto = service.saveUsuario(usuarioDto);
            
            LOG.log(Level.INFO, "Usuario {0} activado correctamente.", usuarioDto.getId());
            return Response.ok().entity(usuarioDto).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    @POST
    @Secured
    @Path("changePassword")
    @ApiOperation(value = "Cambia la contrase\u00f1a por una nueva. Valida que la contrase\u00f1a anterior coincida.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Contrase\u00f1a anterior no coincide"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response changePassword(UsuarioDto usuarioParam) {

        try {
            // Verificamos que la contraseña anterior sea la misma
            PasswordService ps = new PasswordService();
            String encryptedPassword = ps.encrypt(usuarioParam.getPassword());

            UsuarioDto usuarioDto = service.getUsuarioByCredentials(usuarioLogueado.getUsername(), encryptedPassword);

            if (Util.isEmpty(usuarioDto)) {
                LOG.log(Level.WARNING, "Contrase\u00f1a anterior no coincide.");
                return Response.status(Response.Status.NOT_FOUND).entity(Constants.VALIDATION_USUARIO_NOT_FOUND).build();
            }
            
            usuarioDto.setPassword(ps.encrypt(usuarioParam.getNewPassword()));
            usuarioDto = service.saveUsuario(usuarioDto);
            
            // Generar nuevo token?

            return Response.ok().entity(usuarioDto).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
