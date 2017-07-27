package com.minicubic.infoguiahttp;

import com.minicubic.infoguiahttp.dto.ArchivoCabDto;
import com.minicubic.infoguiahttp.dto.ClientePublicacionDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.dto.ValidatorResponse;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.Validator;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import com.minicubic.infoguiahttp.dao.UsuarioAccionDao;
import com.minicubic.infoguiahttp.dto.ListaDeseoDto;
import com.minicubic.infoguiahttp.enums.TableReference;
import com.minicubic.infoguiahttp.model.TipoAccion;
import com.minicubic.infoguiahttp.model.UsuarioAccion;
import com.minicubic.infoguiahttp.services.ArchivoService;
import com.minicubic.infoguiahttp.services.ClientePublicacionService;
import com.minicubic.infoguiahttp.util.converter.UsuarioConverter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
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
 * @version 1
 */
@Path("publicaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/publicaciones", description = "REST Service de Publicaciones")
public class PublicacionRest {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Inject
    private ClientePublicacionService service;
    
    @Inject
    private ArchivoService archivoService;
    
    @Inject
    private UsuarioAccionDao listaDeseoDao;

    private static final Logger LOG = Logger.getLogger("ClientePublicacionesRest");

    @GET
    @Secured
    @Path("/find/all")
    @PermitAll
    @ApiOperation(value = "Obtiene una lista de Publicaciones de Clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findAll() {
        LOG.log(Level.INFO, "Obteniendo listado de Publicaciones de Clientes");

        return Response.ok().entity(service.getClientePublicaciones()).build();
    }

    @GET
    @Secured
    @Path("/find/{id}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Publicacion de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findById(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Obteniendo Publicacion de Cliente por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getClientePublicacion(id)).build();
    }
    
    @GET
    @Secured
    @Path("/findByCliente/{id}")
    @PermitAll
    @ApiOperation(value = "Obtiene un registro de Publicacion de Cliente en base a un ID de Cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByCliente(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "Obteniendo Publicacion de Cliente por id: {0}", new Object[]{id});

        return Response.ok().entity(service.getClientePublicacionesByCliente(id)).build();
    }
    
//    @GET
//    @Secured
//    @Path("/novedades/all")
//    @ApiOperation(value = "Obtiene una lista de Novedades")
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "OK"),
//        @ApiResponse(code = 500, message = "Something wrong in Server")})
//    public Response findNovedades() {
//        LOG.log(Level.INFO, "Obteniendo lista de Novedades");
//
//        return Response.ok().entity(service.getNovedades()).build();
//    }
//    
//    @GET
//    @Secured
//    @Path("/promociones/all")
//    @ApiOperation(value = "Obtiene una lista de Promociones")
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "OK"),
//        @ApiResponse(code = 500, message = "Something wrong in Server")})
//    public Response findPromociones() {
//        LOG.log(Level.INFO, "Obteniendo lista de Promociones");
//
//        return Response.ok().entity(service.getPromociones()).build();
//    }

    @GET
    @Path("/findByTipo/{tipoPublicacion}")
    @PermitAll
    @ApiOperation(value = "Obtiene una lista de Publicidades en base a un tipo de publicidad")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByTipo(@PathParam("tipoPublicacion") Integer tipoPublicacion) {
        LOG.log(Level.INFO, "Obteniendo publicidad por tipo de publicacion: {0}", new Object[]{tipoPublicacion});

        return Response.ok().entity(service.getClientePublicacionesByTipoPublicacion(tipoPublicacion)).build();
    }
    
    @GET
    @Path("/findByCodigo/{codigo}")
    @PermitAll
    @ApiOperation(value = "Obtiene una lista de Publicidades en base a un tipo de publicidad")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findByCodigo(@PathParam("codigo") String codigo) {
        LOG.log(Level.INFO, "Obteniendo publicidad por tipo de publicacion: {0}", new Object[]{codigo});

        return Response.ok().entity(service.getClientePublicacionesByCodigo(codigo)).build();
    }

    @POST
    @Secured
    @Path("/add")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Agrega un registro de Publicacion de Cliente. En el parametro debe venir especificado el tipo de publicacion.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 406, message = "Error de Validacion"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response addClientePublicacion(ClientePublicacionDto clientePublicacionParam) {
        LOG.log(Level.INFO, "Creando nueva Publicacion de Ciente");

        try {

            // Validacion global
            ValidatorResponse<Boolean> validatorResponse = Validator.getInstance().validateAddClientePublicacion(clientePublicacionParam);
            if (!validatorResponse.getData()) {
                LOG.log(Level.WARNING, "Error de validacion");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(validatorResponse.getMensaje()).build();
            }

            clientePublicacionParam = service.saveClientePublicacion(clientePublicacionParam);

            if (Util.isEmpty(clientePublicacionParam)) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
            }

            LOG.log(Level.INFO, "Publicacion de Cliente {0} creado correctamente.", clientePublicacionParam.getId());
            return Response.ok().entity(clientePublicacionParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @PUT
    @Secured
    @Path("/update")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Actualiza un registro de Publicacion de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response updateClientePublicacion(ClientePublicacionDto clientePublicacionParam) {
        LOG.log(Level.INFO, "Actualizando Publicacion de Cliente por id: {0}", new Object[]{clientePublicacionParam.getId()});

        try {

            clientePublicacionParam = service.saveClientePublicacion(clientePublicacionParam);

            LOG.log(Level.INFO, "Publicacion de Cliente {0} actualizado correctamente.", clientePublicacionParam.getId());
            return Response.ok().entity(clientePublicacionParam).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }

    @DELETE
    @Secured
    @Path("/delete/{id}")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Borra un registro de Publicacion de Cliente en base a un ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Error Generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response deleteClientePublicacion(@PathParam("id") Integer id) {
        LOG.log(Level.INFO, "Eliminando Publicacion de Cliente por id: {0}", new Object[]{id});

        try {
            service.deleteClientePublicacion(id);

            LOG.log(Level.INFO, "Publicacion de Cliente {0} eliminado correctamente.", id);
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
        @ApiResponse(code = 404, message = "Registro de Publicacion de Cliente No Encontrado"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response uploadImagenPublicacion(@PathParam("id") Integer id, MultipartFormDataInput input) {
        LOG.log(Level.INFO, "Guardando una Imagen de Perfil");
        
        try {
            // Verificamos que exista el ID de Publicacion de Cliente
            ClientePublicacionDto clientePublicacionDto = service.getClientePublicacion(id);
            
            if ( Util.isEmpty(clientePublicacionDto) ) {
                LOG.log(Level.WARNING, "Registro vacio");
                return Response.status(Response.Status.NOT_FOUND).entity(Constants.MSG_ERROR_DEFAULT).build();
            }
            
            // Guardamos la informacion en DB
            ArchivoCabDto archivoCabDto = archivoService.saveArchivoMultiple(input, clientePublicacionDto);

            LOG.log(Level.INFO, "Imagen de Publicacion {0} agregada correctamente.", id);
            return Response.ok().entity(archivoCabDto).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }        
    }
    
//  ██╗     ██╗███████╗████████╗ █████╗     ██████╗ ███████╗███████╗███████╗ ██████╗ 
//  ██║     ██║██╔════╝╚══██╔══╝██╔══██╗    ██╔══██╗██╔════╝██╔════╝██╔════╝██╔═══██╗
//  ██║     ██║███████╗   ██║   ███████║    ██║  ██║█████╗  ███████╗█████╗  ██║   ██║
//  ██║     ██║╚════██║   ██║   ██╔══██║    ██║  ██║██╔══╝  ╚════██║██╔══╝  ██║   ██║
//  ███████╗██║███████║   ██║   ██║  ██║    ██████╔╝███████╗███████║███████╗╚██████╔╝
//  ╚══════╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝    ╚═════╝ ╚══════╝╚══════╝╚══════╝ ╚═════╝ 
// http://patorjk.com/software/taag/#p=display&c=c%2B%2B&f=ANSI%20Shadow&t=lista%20deseo                                                                                  
    
    @POST
    @Secured
    @Path("/listaDeseos/{idClientePublicacion}")
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @ApiOperation(value = "Marca como lista de deseo una publicacion.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response createListaDeseo(@PathParam("idClientePublicacion") String idClientePublicacion) {
        return createUsuarioAccion(idClientePublicacion, com.minicubic.infoguiahttp.enums.TipoAccion.LISTA_DESEO);
    }

    @DELETE
    @Secured
    @RolesAllowed(Constants.DB_USR_TIPO_ADMIN_ID)
    @Path("/listaDeseos/{idClientePublicacion}")
    @ApiOperation(value = "Elimina un item de la lista de deseo.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response removeListaDeseo(@PathParam("idClientePublicacion") String idClientePublicacion) {
        return removeUsuarioAccion(idClientePublicacion, com.minicubic.infoguiahttp.enums.TipoAccion.LISTA_DESEO);
    }

    @GET
    @Secured
    @Path("/listaDeseos")
    @PermitAll
    @ApiOperation(value = "Obtiene una lista de deseos.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "No tiene permisos suficientes"),
        @ApiResponse(code = 400, message = "Error generico"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response findListaDeseos() {
        return findUsuarioAccions(com.minicubic.infoguiahttp.enums.TipoAccion.LISTA_DESEO);
    }
    
    private Response createUsuarioAccion(String idRef, com.minicubic.infoguiahttp.enums.TipoAccion tipoAccionEnum) {
        LOG.log(Level.INFO, "Insertando UsuarioAccion {0}", tipoAccionEnum.toString());
        try {
            
            // Creamos el registro
            TipoAccion tipoAccion = new TipoAccion();
            tipoAccion.setId(tipoAccionEnum.getId());
            
            UsuarioAccion usuarioAccion = new UsuarioAccion();
            usuarioAccion.setUsuario(new UsuarioConverter().getUsuario(usuarioLogueado));
            usuarioAccion.setTipoAccion(tipoAccion);
            usuarioAccion.setTablaRef(TableReference.CLIENTE_PUBLICACION.getTableName());
            usuarioAccion.setColRef(TableReference.CLIENTE_PUBLICACION.getIdColumnName());
            usuarioAccion.setIdRef(idRef);
            
            if (listaDeseoDao.findByAllParams(usuarioAccion).isEmpty()) {
                listaDeseoDao.create(usuarioAccion);
            }
            
            LOG.log(Level.INFO, "UsuarioAccion agregado correctamente");
            
            return findListaDeseos();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    private Response removeUsuarioAccion(String idRef, com.minicubic.infoguiahttp.enums.TipoAccion tipoAccionEnum) {
        LOG.log(Level.INFO, "Eliminando UsuarioAccion {0}", tipoAccionEnum.toString());
        
        try {

            // Creamos el registro
            TipoAccion tipoAccion = new TipoAccion();
            tipoAccion.setId(tipoAccionEnum.getId());
            
            UsuarioAccion usuarioAccion = new UsuarioAccion();
            usuarioAccion.setUsuario(new UsuarioConverter().getUsuario(usuarioLogueado));
            usuarioAccion.setTipoAccion(tipoAccion);
            usuarioAccion.setTablaRef(TableReference.CLIENTE_PUBLICACION.getTableName());
            usuarioAccion.setColRef(TableReference.CLIENTE_PUBLICACION.getIdColumnName());
            usuarioAccion.setIdRef(idRef);
            
            List<UsuarioAccion> list = listaDeseoDao.findByAllParams(usuarioAccion);
            
            if (!list.isEmpty()) {
                usuarioAccion = list.iterator().next();
                listaDeseoDao.delete(usuarioAccion.getId());
                
                LOG.log(Level.INFO, "UsuarioAccion borrado correctamente");
            }            
            
            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
    
    private Response findUsuarioAccions(com.minicubic.infoguiahttp.enums.TipoAccion tipoAccionEnum) {
        try {
            
            List<UsuarioAccion> favoritoList = listaDeseoDao
                    .findByUsuarioTipoAccion(usuarioLogueado.getId(), tipoAccionEnum.getId());
            
            ClientePublicacionDto publicacionListaDeseo;
            ListaDeseoDto listaDeseoDto = new ListaDeseoDto();
            listaDeseoDto.setListaDeseos(new ArrayList<ClientePublicacionDto>());
            
            for (UsuarioAccion favorito : favoritoList) {
                publicacionListaDeseo = service.getClientePublicacion(Integer.valueOf(favorito.getIdRef()));
                listaDeseoDto.getListaDeseos().add(publicacionListaDeseo);
            }
            
            return Response.ok(listaDeseoDto).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(Constants.MSG_ERROR_DEFAULT).build();
        }
    }
}
