package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.model.Usuario;
import com.minicubic.infoguiahttp.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class UsuarioConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param usuarios
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<UsuarioDto> getUsuariosDto(List<Usuario> usuarios) 
            throws IllegalAccessException, InvocationTargetException {
        List<UsuarioDto> usuariosDto = new ArrayList<>();
        
        for (Usuario usuario : usuarios) {
            usuariosDto.add(getUsuarioDto(usuario));
        }
        
        return usuariosDto;
    }
    
    /**
     * 
     * @param usuariosDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<Usuario> getUsuarios(List<UsuarioDto> usuariosDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<Usuario> usuarios = new ArrayList<>();
        
        for (UsuarioDto usuarioDto : usuariosDto) {
            usuarios.add(getUsuario(usuarioDto));
        }
        
        return usuarios;
    }

    public Usuario getUsuario(UsuarioDto usuarioDto) throws IllegalAccessException, InvocationTargetException {
        Usuario usuario = new Usuario();

        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuario, usuarioDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        // Cargamos las propiedas compuestas
        if ( !Util.isEmpty(usuarioDto.getClienteDto()) )       
            usuario.setCliente(new ClienteConverter().getCliente(usuarioDto.getClienteDto()));
        
        if ( !Util.isEmpty(usuarioDto.getTipoUsuarioDto()) )
            usuario.setTipoUsuario(new TipoUsuarioConverter().getTipoUsuario(usuarioDto.getTipoUsuarioDto()));
        
        if ( !Util.isEmpty(usuarioDto.getEstadoUsuarioDto()) ) {
            usuario.setEstadoUsuario(new EstadoUsuarioConverter().getEstadoUsuario(usuarioDto.getEstadoUsuarioDto()));
        }

        return usuario;
    }
    
    public UsuarioDto getUsuarioDto(Usuario usuario) throws IllegalAccessException, InvocationTargetException {
        UsuarioDto usuarioDto = new UsuarioDto();

        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuarioDto, usuario);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        // Cargamos las propiedas compuestas
        if ( !Util.isEmpty(usuario.getCliente()) )       
            usuarioDto.setClienteDto(new ClienteConverter().getClienteDto(usuario.getCliente()));
        
        if ( !Util.isEmpty(usuario.getTipoUsuario()) )
            usuarioDto.setTipoUsuarioDto(new TipoUsuarioConverter().getTipoUsuarioDto(usuario.getTipoUsuario()));
        
        if ( !Util.isEmpty(usuario.getEstadoUsuario()) ) {
            usuarioDto.setEstadoUsuarioDto(new EstadoUsuarioConverter().getEstadoUsuarioDto(usuario.getEstadoUsuario()));
        }
        
        // Por cuestion de seguridad, no enviamos el password
        usuarioDto.setPassword(null);

        return usuarioDto;
    }
}
