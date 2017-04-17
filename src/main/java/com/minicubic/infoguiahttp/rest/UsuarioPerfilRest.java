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
import com.minicubic.infoguiahttp.services.UsuarioPerfilService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

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
    @Path("/upload")
    @Consumes("multipart/form-data")
    @ApiOperation(value = "Carga un archivo en el servidor.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response uploadFile(@MultipartForm FileUploadDto form) {

        String fileName = form.getFileName() == null ? "Unknown" : form.getFileName() ;
         
        String completeFilePath = Constants.UPLOAD_DIR + fileName;
        try
        {
            //Save the file
            File file = new File(completeFilePath);
              
            if (!file.exists()) 
            {
                file.createNewFile();
            }
      
            FileOutputStream fos = new FileOutputStream(file);
      
            fos.write(form.getFileData());
            fos.flush();
            fos.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //Build a response to return
        return Response.status(200)
            .entity("uploadFile is called, Uploaded file name : " + fileName).build();
    }
}
