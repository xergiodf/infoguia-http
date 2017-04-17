package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.FileUploadDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.UsuarioPerfilDto;
import com.minicubic.infoguiacore.dto.ValidatorResponse;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.ArchivoService;
import com.minicubic.infoguiahttp.services.UsuarioPerfilService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author xergio
 * @version 1
 */
@Singleton
@Path("usuarioPerfiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/usuarioPerfiles", description = "REST Service de Perfiles de Usuarios")
public class UsuarioPerfilRest {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private UsuarioPerfilService service;
    
    @Inject
    private ArchivoService archivoService;
    
    private static final Logger LOG = Logger.getLogger("UsuarioPerfilesRest");
    
    @GET
    @Secured
    @Path("/find/all")
    @ApiOperation(value = "Obtiene una lista de Perfiles de Usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de perfiles de usuarios");
        
        return Response.ok().entity(service.getUsuarioPerfiles()).build();
    }
 
    @GET
    @Secured
    @Path("/find/{id}")
    @ApiOperation(value = "Obtiene un registro de Perfil de Usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo perfil de usuario por id: {0}", new Object[]{id});
        
        return Response.ok().entity(service.getUsuarioPerfil(id)).build();
    }
    
    @POST
    @Secured
    @Path("/add")
    @ApiOperation(value = "Agrega un registro de Perfil de Usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addUsuario(UsuarioPerfilDto usuarioParam) {
        LOG.log(Level.INFO, "Creando nuevo perfil de usuario");

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddUsuarioPerfil(usuarioParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }

            usuarioParam = service.saveUsuarioPerfil(usuarioParam);

            if (Util.isEmpty(usuarioParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Perfil de Usuario {0} creado correctamente.", usuarioParam.getId());
            return Response.ok().entity(usuarioParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
 
    @PUT
    @Secured
    @Path("/update")
    @ApiOperation(value = "Actualiza un registro de Usuario. Servicio no implementado aun.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateUsuario(UsuarioPerfilDto usuarioParam) {
        LOG.log(Level.INFO, "Actualizando perfil de usuario por id: {0}", new Object[]{usuarioParam.getId()});
        
        try {

            usuarioParam = service.saveUsuarioPerfil(usuarioParam);

            LOG.log(Level.INFO, "Perfil de Usuario {0} actualizado correctamente.", usuarioParam.getId());
            return Response.ok().entity(usuarioParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
 
    @DELETE
    @Secured
    @Path("/delete/{id}")
    @ApiOperation(value = "Elimina un registro de Perfil de Usuario.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteUsuario(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando perfil de usuario por id: {0}", new Object[]{id});
        
        try {
            service.deleteUsuarioPerfil(id);

            LOG.log(Level.INFO, "Perfil de Usuario {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    @POST
    @Secured
    @Path("/upload/{id}")
    @Consumes("multipart/form-data")
    @ApiOperation(value = "Carga un archivo en el servidor.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Registros de Perfil de Usuario No Encontrado"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response uploadFotoPerfil(@PathParam("id") Integer id, MultipartFormDataInput input) {
        LOG.log(Level.INFO, "Guardando una Foto de Perfil");
        
        try {
            // Verificamos que exista el ID de Perfil de Usuario
            UsuarioPerfilDto usuarioPerfilDto = service.getUsuarioPerfil(id);
            
            if ( Util.isEmpty(usuarioPerfilDto) ) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.NOT_FOUND).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            // Guardamos la imagen en disco
            String fileName = UUID.randomUUID().toString();
            
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
            InputPart inputPart = uploadForm.get(Constants.FILE_FORM_NAME).get(0);
            
            MultivaluedMap<String, String> header = inputPart.getHeaders();

            // Guardamos la informacion en DB

            LOG.log(Level.INFO, "Foto de Perfil {0} agregada correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }        
    }
}
