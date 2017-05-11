package com.minicubic.infoguiacore.util;

import com.minicubic.infoguiacore.dto.CategoriaDto;
import com.minicubic.infoguiacore.dto.CiudadDto;
import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.ClientePublicacionDto;
import com.minicubic.infoguiacore.dto.ClienteSucursalDto;
import com.minicubic.infoguiacore.dto.DepartamentoDto;
import com.minicubic.infoguiacore.dto.EstadoPublicacionDto;
import com.minicubic.infoguiacore.dto.EstadoUsuarioDto;
import com.minicubic.infoguiacore.dto.TipoPublicacionDto;
import com.minicubic.infoguiacore.dto.TipoUsuarioDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.UsuarioPerfilDto;
import com.minicubic.infoguiacore.dto.ValidatorResponse;

/**
 *
 * @author xergio
 * @version 3 - 09/04/2017
 */
public class Validator {

    private static Validator INSTANCE = null;

    private Validator() {
    }

    public static Validator getInstance() {
        if (Util.isEmpty(INSTANCE)) {
            INSTANCE = new Validator();
        }
        return INSTANCE;
    }

    /**
     *
     * @param usuarioDto
     * @return
     */
    public ValidatorResponse<Boolean> validateAddUsuario(UsuarioDto usuarioDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);

        if (Util.isEmpty(usuarioDto.getEmail())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_EMAIL_REQUIRED));
        }

        if (Util.isEmpty(usuarioDto.getUsername())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_USERNAME_REQUIRED));
        }

        if (Util.isEmpty(usuarioDto.getPassword())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_PASSWORD_REQUIRED));
        }

        if (Util.isEmpty(usuarioDto.getEstadoUsuarioDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_ESTADOUSUARIO_REQUIRED));
        }

        if (Util.isEmpty(usuarioDto.getTipoUsuarioDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_ESTADOUSUARIO_REQUIRED));
        }

        return response;
    }

    public ValidatorResponse<Boolean> validateAddUsuarioSocial(UsuarioDto usuarioDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);

        if (Util.isEmpty(usuarioDto.getEmail())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_EMAIL_REQUIRED));
        }

        if (Util.isEmpty(usuarioDto.getUsername())) {
            if (!Util.isEmpty(usuarioDto.getNombreSocial())) {
                usuarioDto.setUsername(usuarioDto.getNombreSocial().trim().replace(" ", "").toLowerCase());
            } else {
                response.setData(false);
                response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_EMAIL_REQUIRED));
            }
        }

        return response;
    }

    /**
     *
     * @param clienteDto
     * @return
     */
    public ValidatorResponse<Boolean> validateAddCliente(ClienteDto clienteDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);

        if (Util.isEmpty(clienteDto.getNombreCompleto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_CLIENTE_NOMBRECOMPLETO_REQUIRED));
        }

        if (Util.isEmpty(clienteDto.getCodigoCliente())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_CLIENTE_CODIGO_CLIENTE_REQUIRED));
        }

        return response;
    }

    /**
     *
     * @param clientePublicacionDto
     * @return
     */
    public ValidatorResponse<Boolean> validateAddClientePublicacion(ClientePublicacionDto clientePublicacionDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);

        if (Util.isEmpty(clientePublicacionDto.getTipoPublicacionDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_PUBLICACION_TIPOPUBLICACION_REQUIRED));
        }

        if (Util.isEmpty(clientePublicacionDto.getClienteDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_PUBLICACION_CLIENTE_REQUIRED));
        }

        if (Util.isEmpty(clientePublicacionDto.getEstadoPublicacionDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_PUBLICACION_ESTADO_REQUIRED));
        }

        if (Util.isEmpty(clientePublicacionDto.getFechaDesde())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_PUBLICACION_FECHADESDE_REQUIRED));
        }

        return response;
    }

    /**
     *
     * @param clienteSucursalDto
     * @return
     */
    public ValidatorResponse<Boolean> validateAddClienteSucursal(ClienteSucursalDto clienteSucursalDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);

        if (Util.isEmpty(clienteSucursalDto.getNombreSucursal())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_SUCURSAL_NOMBRE_REQUIRED));
        }

        if (Util.isEmpty(clienteSucursalDto.getDireccionFisica())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_SUCURSAL_DIRECCION_REQUIRED));
        }

        if (Util.isEmpty(clienteSucursalDto.getCoordenadas())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_SUCURSAL_COORDENADAS_REQUIRED));
        }

        if (Util.isEmpty(clienteSucursalDto.getClienteDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_SUCURSAL_CLIENTE_REQUIRED));
        }

        return response;
    }

    /**
     * 
     * @param categoriaDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddCategoria(CategoriaDto categoriaDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);

        if (Util.isEmpty(categoriaDto.getDescripcion())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_CATEGORIA_DESCRIPCION));
        }

        if (Util.isEmpty(categoriaDto.getGrupoCategoriaDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_CATEGORIA_GRUPO));
        }

        return response;
    }

    /**
     * 
     * @param usuarioPerfilDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddUsuarioPerfil(UsuarioPerfilDto usuarioPerfilDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);

        if (Util.isEmpty(usuarioPerfilDto.getNombres())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_PERFIL_NOMBRES));
        }

        if (Util.isEmpty(usuarioPerfilDto.getApellidos())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_PERFIL_APELLIDOS));
        }

        if (Util.isEmpty(usuarioPerfilDto.getUsuarioDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_PERFIL_USUARIO));
        }

        return response;
    }
    
    /**
     * 
     * @param tipoPublicacionDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddTipoPublicacion(TipoPublicacionDto tipoPublicacionDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);
        
        if (Util.isEmpty(tipoPublicacionDto.getDescripcion())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_GENERIC_DESCRIPCION));
        }
        
        return response;
    }
    
    /**
     * 
     * @param estadoPublicacionDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddEstadoPublicacion(EstadoPublicacionDto estadoPublicacionDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);
        
        if (Util.isEmpty(estadoPublicacionDto.getDescripcion())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_GENERIC_DESCRIPCION));
        }
        
        return response;
    }
    
    /**
     * 
     * @param tipoUsuarioDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddTipoUsuario(TipoUsuarioDto tipoUsuarioDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);
        
        if (Util.isEmpty(tipoUsuarioDto.getDescripcion())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_GENERIC_DESCRIPCION));
        }
        
        return response;
    }
    
    /**
     * 
     * @param estadoUsuarioDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddEstadoUsuario(EstadoUsuarioDto estadoUsuarioDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);
        
        if (Util.isEmpty(estadoUsuarioDto.getDescripcion())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_GENERIC_DESCRIPCION));
        }
        
        return response;
    }
    
    /**
     * 
     * @param departamentoDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddDepartamento(DepartamentoDto departamentoDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);
        
        if (Util.isEmpty(departamentoDto.getDescripcion())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_GENERIC_DESCRIPCION));
        }
        
        return response;
    }
    
    public ValidatorResponse<Boolean> validateAddCiudad(CiudadDto ciudadDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(true);
        
        if (Util.isEmpty(ciudadDto.getDescripcion())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_GENERIC_DESCRIPCION));
        }
        
        if (Util.isEmpty(ciudadDto.getDepartamentoDto())) {
            response.setData(false);
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_CIUDAD_DEPARTAMENTO));
        }
        
        return response;
    }
}
