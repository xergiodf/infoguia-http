package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.UsuarioAccionDto;
import com.minicubic.infoguiahttp.model.UsuarioAccion;
import com.minicubic.infoguiahttp.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author sedf
 */
public class UsuarioAccionConverter {
    
    private static UsuarioAccionConverter instance = null;
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    private UsuarioAccionConverter() {}
    public static UsuarioAccionConverter getInstance() {
        if ( instance == null ) 
            instance = new UsuarioAccionConverter();
        
        return instance;
    }
    
    /**
     * 
     * @param usuarioAccions
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<UsuarioAccionDto> getUsuarioAccionsDto(List<UsuarioAccion> usuarioAccions) 
            throws IllegalAccessException, InvocationTargetException {
        List<UsuarioAccionDto> usuarioAccionsDto = new ArrayList<>();
        
        for (UsuarioAccion usuarioAccion : usuarioAccions) {
            usuarioAccionsDto.add(getUsuarioAccionDto(usuarioAccion));
        }
        
        return usuarioAccionsDto;
    }
    
    /**
     * 
     * @param usuarioAccionsDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<UsuarioAccion> getUsuarioAccions(List<UsuarioAccionDto> usuarioAccionsDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<UsuarioAccion> usuarioAccions = new ArrayList<>();
        
        for (UsuarioAccionDto usuarioAccionDto : usuarioAccionsDto) {
            usuarioAccions.add(getUsuarioAccion(usuarioAccionDto));
        }
        
        return usuarioAccions;
    }
    
    public UsuarioAccion getUsuarioAccion(UsuarioAccionDto usuarioAccionDto) throws IllegalAccessException, InvocationTargetException {
        UsuarioAccion usuarioAccion = new UsuarioAccion();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuarioAccion, usuarioAccionDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(usuarioAccionDto.getTipoAccionDto()) ) 
            usuarioAccion.setTipoAccion(new TipoAccionConverter()
                    .getTipoAccion(usuarioAccionDto.getTipoAccionDto()));
        
        if ( !Util.isEmpty(usuarioAccionDto.getUsuarioDto()) )
            usuarioAccion.setUsuario(new UsuarioConverter()
                    .getUsuario(usuarioAccionDto.getUsuarioDto()));
        
        return usuarioAccion;
    }
    
    public UsuarioAccionDto getUsuarioAccionDto(UsuarioAccion usuarioAccion) throws IllegalAccessException, InvocationTargetException {
        UsuarioAccionDto usuarioAccionDto = new UsuarioAccionDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuarioAccionDto, usuarioAccion);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(usuarioAccion.getTipoAccion()) ) 
            usuarioAccionDto.setTipoAccionDto(new TipoAccionConverter()
                    .getTipoAccionDto(usuarioAccion.getTipoAccion()));
        
        if ( !Util.isEmpty(usuarioAccion.getUsuario()) )
            usuarioAccionDto.setUsuarioDto(new UsuarioConverter()
                    .getUsuarioDto(usuarioAccion.getUsuario()));
        
        return usuarioAccionDto;
    }
}
