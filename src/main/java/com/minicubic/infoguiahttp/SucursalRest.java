package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.dto.ArchivoCabDto;
import com.minicubic.infoguiahttp.dto.ClienteSucursalDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.dto.ValidatorResponse;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.services.ArchivoService;
import com.minicubic.infoguiahttp.services.ClienteSucursalService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author xergio
 * @version 2 - 20/04/2017
 */
@Path("sucursales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/sucursales", description = "REST Service de Sucursales")
public class SucursalRest {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private ArchivoService archivoService;

    @Inject
    private ClienteSucursalService service;

    private static final Logger LOG = Logger.getLogger("ClienteSucursalesRest");

    @GET
    @Secured
    @Path("/find/all")
    @PermitAll
    @ApiOperation(value = "Obtiene una lista de Sucursales de Clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de Sucursales de Clientes");

        return Response.ok().entity(service.getClienteSucursales()).build();
    }

    @GET
    @Secured
    @Path("/find/{id}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Sucursal de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Sucursal de Cliente No Encontrada"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo Sucursal de Cliente por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getClienteSucursal(id)).build();
    }
    
    @GET
    @Secured
    @Path("/findByCliente/{id}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Sucursal de Cliente en base a un ID de Cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByCliente(@PathParam("id") Long clienteId) {
        LOG.log(Level.INFO, "Obteniendo Sucursal de Cliente por id: {0}", new Object[]{clienteId});

        return Response.ok().entity(service.getClienteSucursalesByCliente(clienteId)).build();
    }

    @POST
    @Secured
    @Path("/add")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Agrega un registro de Sucursal de Cliente en base a un ID. En el parametro debe venir especificado el tipo de publicacion (Novedad o Promocion.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addClienteSucursal(ClienteSucursalDto clienteSucursalParam) {
        LOG.log(Level.INFO, "Creando nueva Sucursal de Ciente");

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddClienteSucursal(clienteSucursalParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }

            clienteSucursalParam = service.saveClienteSucursal(clienteSucursalParam);

            if (Util.isEmpty(clienteSucursalParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Sucursal de Cliente {0} creado correctamente.", clienteSucursalParam.getId());
            return Response.ok().entity(clienteSucursalParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @PUT
    @Secured
    @Path("/update")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Actualiza un registro de Sucursal de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateClienteSucursal(ClienteSucursalDto clienteSucursalParam) {
        LOG.log(Level.INFO, "Actualizando Sucursal de Cliente por id: {0}", new Object[]{clienteSucursalParam.getId()});

        try {

            clienteSucursalParam = service.saveClienteSucursal(clienteSucursalParam);

            LOG.log(Level.INFO, "Sucursal de Cliente {0} actualizado correctamente.", clienteSucursalParam.getId());
            return Response.ok().entity(clienteSucursalParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Borra un registro de Sucursal de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteClienteSucursal(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando Sucursal de Cliente por id: {0}", new Object[]{id});

        try {
            service.deleteClienteSucursal(id);

            LOG.log(Level.INFO, "Sucursal de Cliente {0} eliminado correctamente.", id);
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    @POST
    @Secured
    @Path("/upload/{id}")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @Consumes("multipart/form-data")
    @ApiOperation(value = "Carga un archivo en el servidor.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Registro de Sucursal No Encontrado"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response uploadImagenPortada(@PathParam("id") Integer id, MultipartFormDataInput input) {
        LOG.log(Level.INFO, "Guardando una Imagen de Portada de Sucursal");
        
        try {
            // Verificamos que exista el ID de Perfil de Usuario
            ClienteSucursalDto clienteSucursalDto = service.getClienteSucursal(id);
            
            if ( Util.isEmpty(clienteSucursalDto) ) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.NOT_FOUND).entity(Constants.MSG_ERROR_DEFAULT).build();
            }
            
            // Guardamos la informacion en DB
            ArchivoCabDto archivoCabDto = archivoService.saveArchivo(input, clienteSucursalDto);

            LOG.log(Level.INFO, "Imagen de Portada de Sucursal {0} agregada correctamente.", id);
            return Response.ok().entity(archivoCabDto).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }        
    }
    
//  ██╗  ██╗ ██████╗ ██████╗  █████╗ ██████╗ ██╗ ██████╗ ███████╗
//  ██║  ██║██╔═══██╗██╔══██╗██╔══██╗██╔══██╗██║██╔═══██╗██╔════╝
//  ███████║██║   ██║██████╔╝███████║██████╔╝██║██║   ██║███████╗
//  ██╔══██║██║   ██║██╔══██╗██╔══██║██╔══██╗██║██║   ██║╚════██║
//  ██║  ██║╚██████╔╝██║  ██║██║  ██║██║  ██║██║╚██████╔╝███████║
//  ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝ ╚══════╝
// http://patorjk.com/software/taag/#p=display&c=c%2B%2B&f=ANSI%20Shadow&t=horarios
    
    
//  ██╗     ██╗███████╗████████╗ █████╗     ██████╗ ███████╗███████╗███████╗ ██████╗ 
//  ██║     ██║██╔════╝╚══██╔══╝██╔══██╗    ██╔══██╗██╔════╝██╔════╝██╔════╝██╔═══██╗
//  ██║     ██║███████╗   ██║   ███████║    ██║  ██║█████╗  ███████╗█████╗  ██║   ██║
//  ██║     ██║╚════██║   ██║   ██╔══██║    ██║  ██║██╔══╝  ╚════██║██╔══╝  ██║   ██║
//  ███████╗██║███████║   ██║   ██║  ██║    ██████╔╝███████╗███████║███████╗╚██████╔╝
//  ╚══════╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝    ╚═════╝ ╚══════╝╚══════╝╚══════╝ ╚═════╝ 
// http://patorjk.com/software/taag/#p=display&c=c%2B%2B&f=ANSI%20Shadow&t=lista%20deseo
    
    
}
