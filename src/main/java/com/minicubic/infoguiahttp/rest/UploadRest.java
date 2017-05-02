package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.ArchivoService;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xergio
 * @version 1 - 02.05.2017
 */
@Singleton
@Path("upload")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/upload", description = "REST Service de Upload")
public class UploadRest {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private ArchivoService service;
    
    private static final Logger LOG = Logger.getLogger("UploadRest");
    
    @DELETE
    @Secured
    @Path("/delete/{id}")
    @ApiOperation(value = "Borra un Registro de Archivo de la BD y tambien el archivo fisico en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteTipoUsuario(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando archivo por id: {0}", new Object[]{id});

        try {
//            service.deleteTipoUsuario(id);

            LOG.log(Level.INFO, "Archivo {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
