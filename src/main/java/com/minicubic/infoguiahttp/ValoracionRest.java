package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.dto.SucursalValoracionCabDto;
import com.minicubic.infoguiahttp.dto.ValidatorResponse;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.Validator;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.services.SucursalValoracionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
 * @version 1 - 08.06.2017
 */
@Path("valoraciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/valoraciones", description = "REST Service de Valoraciones")
public class ValoracionRest {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private SucursalValoracionService service;

    private static final Logger LOG = Logger.getLogger("ValoracionesRest");

    @GET
    @Secured
    @Path("/find/{id}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Valoracions en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo valoracion por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getSucursalValoracionCab(id)).build();
    }
    
    @GET
    @Secured
    @Path("/findByCliente/{idCliente}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Valoracions en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByCliente(@PathParam("idCliente") Long idCliente) {
        LOG.log(Level.INFO, "Obteniendo valoracion por id: {0}", new Object[]{idCliente});

        return Response.ok().entity(service.getSucursalValoracionesCabByCliente(idCliente)).build();
    }
    
    @GET
    @Secured
    @Path("/findBySucursal/{idClienteSucursal}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Valoracions en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByClienteSucursal(@PathParam("idClienteSucursal") Integer idClienteSucursal) {
        LOG.log(Level.INFO, "Obteniendo valoracion por id: {0}", new Object[]{idClienteSucursal});

        return Response.ok().entity(service.getSucursalValoracionesCabByClienteSucursal(idClienteSucursal)).build();
    }

    @POST
    @Secured
    @Path("/add")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Agrega un registro de Publicacion de Valoracion")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addValoracion(SucursalValoracionCabDto valoracionParam) {
        LOG.log(Level.INFO, "Creando nuevo valoracion");

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddValoracion(valoracionParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }

            valoracionParam.getSucursalValoracionesDetDto().iterator().next().setUsuarioDto(usuarioLogueado);
            valoracionParam = service.saveSucursalValoracionCab(valoracionParam);

            if (Util.isEmpty(valoracionParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Valoracion {0} creado correctamente.", valoracionParam.getId());
            return Response.ok().entity(valoracionParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

//    @PUT
//    @Secured
//    @Path("/update")
//    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
//    @ApiOperation(value = "Actualiza un registro de Valoracions en base a un ID")
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "OK"),
//        @ApiResponse(code = 400, message = "Error Generico"),
//        @ApiResponse(code = 500, message = "Something wrong in Server")})
//    public Response updateValoracion(SucursalValoracionCabDto valoracionParam) {
//        LOG.log(Level.INFO, "Actualizando valoracion por id: {0}", new Object[]{valoracionParam.getId()});
//
//        try {
//
//            valoracionParam = service.saveSucursalValoracionCab(valoracionParam);
//
//            LOG.log(Level.INFO, "Valoracion {0} actualizado correctamente.", valoracionParam.getId());
//            return Response.ok().entity(valoracionParam).build();
//        } catch (Exception ex) {
//            LOG.log(Level.SEVERE, null, ex);
//            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
//        }
//    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Borra un registro de Valoracions en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteValoracion(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando valoracion por id: {0}", new Object[]{id});

        try {
            service.deleteSucursalValoracion(id);

            LOG.log(Level.INFO, "Valoracion {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
