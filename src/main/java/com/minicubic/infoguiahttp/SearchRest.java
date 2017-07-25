package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.dto.SearchRequestDto;
import com.minicubic.infoguiahttp.services.ClienteSucursalService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import javax.annotation.security.PermitAll;
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
 * @version 1 - 12.06.2017
 */
@Path("search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/search", description = "REST Service de Busqueda")
public class SearchRest {
    
    @Inject
    private ClienteSucursalService clienteSucursalService;

//    @GET
//    @Secured
//    @Path("/clientes/{query}")
//    @PermitAll
//    @ApiOperation(value = "Busca un registro de Cliente en base al query de busqueda")
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "OK"),
//        @ApiResponse(code = 406, message = "Error de Validacion"),
//        @ApiResponse(code = 400, message = "Error generico"),
//        @ApiResponse(code = 500, message = "Something wrong in Server")})
//    public Response searchClientes(@PathParam("query") String query) {
//        return Response.ok().entity(clienteSucursalService.getClienteSucursalesByParams(query)).build();
//    }
    
    /**
     * @param searchDto
     * @return 
     */
    @POST
    @Secured
    @PermitAll
    @Path("/clientes")
    @ApiOperation(value = "Busca un registro de Cliente en base al query de busqueda")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response searchClientesFiltros(SearchRequestDto searchDto) {
        return Response.ok().entity(clienteSucursalService.getClienteSucursalesByParams(searchDto)).build();
    }
}
