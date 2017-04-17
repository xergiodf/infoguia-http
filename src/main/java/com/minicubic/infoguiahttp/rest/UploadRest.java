package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.FileUploadDto;
import com.minicubic.infoguiacore.util.Constants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

/**
 *
 * @author xergio
 * @version 1 - 12/04/2017
 */
@Singleton
@Path("upload")
@Api(value = "/upload", description = "REST Service para subida de archivos")
public class UploadRest {

    @POST
    @Path("/file")
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
