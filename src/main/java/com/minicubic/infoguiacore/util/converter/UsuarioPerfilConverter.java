package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.UsuarioPerfilDto;
import com.minicubic.infoguiacore.model.UsuarioPerfil;
import com.minicubic.infoguiacore.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class UsuarioPerfilConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param usuarioPerfiles
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<UsuarioPerfilDto> getUsuarioPerfilesDto(List<UsuarioPerfil> usuarioPerfiles) 
            throws IllegalAccessException, InvocationTargetException {
        List<UsuarioPerfilDto> usuarioPerfilesDto = new ArrayList<>();
        
        for (UsuarioPerfil usuarioPerfil : usuarioPerfiles) {
            usuarioPerfilesDto.add(getUsuarioPerfilDto(usuarioPerfil));
        }
        
        return usuarioPerfilesDto;
    }
    
    /**
     * 
     * @param usuarioPerfilesDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<UsuarioPerfil> getUsuarioPerfiles(List<UsuarioPerfilDto> usuarioPerfilesDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<UsuarioPerfil> usuarioPerfiles = new ArrayList<>();
        
        for (UsuarioPerfilDto usuarioPerfilDto : usuarioPerfilesDto) {
            usuarioPerfiles.add(getUsuarioPerfil(usuarioPerfilDto));
        }
        
        return usuarioPerfiles;
    }
 
    /**
     * 
     * @param usuarioPerfilDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public UsuarioPerfil getUsuarioPerfil(UsuarioPerfilDto usuarioPerfilDto) 
            throws IllegalAccessException, InvocationTargetException {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuarioPerfil, usuarioPerfilDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Cargamos las propiedades compuestas
        if ( !Util.isEmpty(usuarioPerfilDto.getUsuarioDto()) ) {
            usuarioPerfil.setUsuario(new UsuarioConverter()
                    .getUsuario(usuarioPerfilDto.getUsuarioDto()));
        }

        return usuarioPerfil;
    }
    
    /**
     * 
     * @param usuarioPerfil
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public UsuarioPerfilDto getUsuarioPerfilDto(UsuarioPerfil usuarioPerfil) 
            throws IllegalAccessException, InvocationTargetException {
        UsuarioPerfilDto usuarioPerfilDto = new UsuarioPerfilDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuarioPerfilDto, usuarioPerfil);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Cargamos las propiedades compuestas
        if ( !Util.isEmpty(usuarioPerfil.getUsuario()) ) {
            usuarioPerfilDto.setUsuarioDto(new UsuarioConverter()
                    .getUsuarioDto(usuarioPerfil.getUsuario()));
        }
        
        return usuarioPerfilDto;
    }
}
