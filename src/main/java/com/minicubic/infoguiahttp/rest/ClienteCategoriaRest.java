package com.minicubic.infoguiahttp.rest;

import com.minicubic.infoguiacore.dto.ClienteCategoriaDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.ClienteCategoriaPK;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.ClienteCategoriaService;
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
 * @version 1
 */
@Singleton
@Path("clienteCategorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/clienteCategorias", description = "REST Service de ClienteCategorias")
public class ClienteCategoriaRest {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject 
    ClienteCategoriaService service;

    private static final Logger LOG = Logger.getLogger("ClienteCategoriasRest");
    
    @GET
    @Secured
    @Path("/findByCliente/{id}")
    @ApiOperation(value = "Obtiene una lista de registros de Clientes Categorias en base a un ID de Cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByCliente(@PathParam("id") Long idCliente) {
        LOG.log(Level.INFO, "Obteniendo Cliente Categoria por idCliente: {0}", new Object[]{idCliente});

        return Response.ok().entity(service.getClienteCategoriasByCliente(idCliente)).build();
    }
    
    @GET
    @Secured
    @Path("/findByCategoria/{id}")
    @ApiOperation(value = "Obtiene una lista de registros de Clientes Categorias en base a un ID de Categoria")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByCategoria(@PathParam("id") Integer idCategoria) {
        LOG.log(Level.INFO, "Obteniendo Cliente Categoria por idCategoria: {0}", new Object[]{idCategoria});

        return Response.ok().entity(service.getClienteCategoriasByCategoria(idCategoria)).build();
    }
    
    @POST
    @Secured
    @Path("/add")
    @ApiOperation(value = "Agrega un registro de Publicacion de Cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addClienteCategoria(ClienteCategoriaDto clienteCategoriaParam) {
        LOG.log(Level.INFO, "Creando nuevo cliente");

        try {

            clienteCategoriaParam = service.saveClienteCategoria(clienteCategoriaParam);

            if (Util.isEmpty(clienteCategoriaParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "ClienteCategoria {0}-{1} creado correctamente.", 
                    new Object[]{clienteCategoriaParam.getClienteDto().getId(), clienteCategoriaParam.getCategoriaDto().getId()});
            return Response.ok().entity(clienteCategoriaParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    @DELETE
    @Secured
    @Path("/delete/{idCliente}/{idCategoria}")
    @ApiOperation(value = "Borra un registro de Clientes en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteClienteCategoria(@PathParam(value = "idCliente") Long idCliente, 
            @PathParam(value = "idCategoria") Integer idCategoria) {
        LOG.log(Level.INFO, "Eliminando ClienteCategoria por id: {0}-{1}", 
                new Object[]{idCliente, idCategoria});

        try {
            
            ClienteCategoriaPK id = new ClienteCategoriaPK(idCliente, idCategoria);
            
            service.deleteClienteCategoria(id);

            LOG.log(Level.INFO, "ClienteCategoria {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
