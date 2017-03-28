package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.Usuario;
import com.minicubic.infoguiacore.util.Util;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class UsuarioConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();

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
        
        if ( !Util.isEmpty(usuarioDto.getUsuarioEstadoDto()) ) {
            usuario.setUsuarioEstado(new EstadoUsuarioConverter().getEstadoUsuario(usuarioDto.getUsuarioEstadoDto()));
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
        
        if ( !Util.isEmpty(usuario.getUsuarioEstado()) ) {
            usuarioDto.setUsuarioEstadoDto(new EstadoUsuarioConverter().getEstadoUsuarioDto(usuario.getUsuarioEstado()));
        }
        
        // Por cuestion de seguridad, no enviamos el password
        usuarioDto.setPassword(null);

        return usuarioDto;
    }
}
