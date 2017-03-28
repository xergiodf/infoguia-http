package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.TipoUsuarioDto;
import com.minicubic.infoguiacore.model.TipoUsuario;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoUsuarioConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    public TipoUsuario getTipoUsuario(TipoUsuarioDto tipoUsuarioDto) throws IllegalAccessException, InvocationTargetException {
        TipoUsuario tipoUsuario = new TipoUsuario();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoUsuario, tipoUsuarioDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoUsuario;
    }
    
    public TipoUsuarioDto getTipoUsuarioDto(TipoUsuario tipoUsuario) throws IllegalAccessException, InvocationTargetException {
        TipoUsuarioDto tipoUsuarioDto = new TipoUsuarioDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoUsuarioDto, tipoUsuario);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoUsuarioDto;
    }
}
