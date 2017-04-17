package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.FileUploadDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.services.ArchivoService;
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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

/**
 * Servicio REST para Upload de Imagenes
 * FOTO DE PERFIL DE USUARIO
 * FOTO DE PERFIL DE CLIENTE
 * GALERIA DE IMAGENES DE CLIENTE
 * 
 * 
 * @author xergio
 * @version 1 - 12/04/2017
 */
@Singleton
@Path("upload")
@Api(value = "/upload", description = "REST Service para subida de archivos")
public class UploadRest {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Inject
    private ArchivoService service;
    
    @Inject
    private UsuarioPerfilService usuarioPerfilService;

    private static final Logger LOG = Logger.getLogger("UploadRest");

    @POST
    @Path("/fotoPerfil")
    @Consumes("multipart/form-data")
    @ApiOperation(value = "Carga una foto de perfil en el servidor.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response uploadFotoPerfil(@MultipartForm FileUploadDto fileUploadDto) {
        LOG.log(Level.INFO, "Guardando una Foto de Perfil");
        
        // Verificamos que exista el ID de Perfil de Usuario
        
        
        // Guardamos la imagen en disco
        
        // Guardamos la informacion en DB
        
        String fileName = fileUploadDto.getReferenceId() == null ? "Unknown" : fileUploadDto.getReferenceId() ;
         
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
      
            fos.write(fileUploadDto.getFileData());
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
