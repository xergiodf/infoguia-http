package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.ClienteSucursalService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xergio
 * @version 1 - 12.06.2017
 */
@Singleton
@Path("search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/search", description = "REST Service de Busqueda")
public class SearchRest {

    
    @Inject
    private ClienteSucursalService clienteSucursalService;

    @GET
    @Secured
    @Path("/clientes/{query}")
    @ApiOperation(value = "Busca un registro de Cliente en base al query de busqueda")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response searchClientes(@PathParam("query") String query) {
        return Response.ok().entity(clienteSucursalService.getClienteSucursalesByParams(query)).build();
    }
    
    /**
     * Los filtros vienen de esta manera:
     * categoria:valor|cliente:valor
     * @param query
     * @return 
     */
    @GET
    @Secured
    @Path("/clientes/{query}/{filtros}")
    @ApiOperation(value = "Busca un registro de Cliente en base al query de busqueda")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response searchClientesFiltros(@PathParam("query") String query) {
        return Response.ok().entity(clienteSucursalService.getClienteSucursalesByParams(query)).build();
    }
}