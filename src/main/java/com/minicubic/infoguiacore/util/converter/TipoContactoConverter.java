package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.TipoContactoDto;
import com.minicubic.infoguiacore.model.TipoContacto;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoContactoConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    public TipoContacto getTipoContacto(TipoContactoDto tipoContactoDto) throws IllegalAccessException, InvocationTargetException {
        TipoContacto tipoContacto = new TipoContacto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoContacto, tipoContactoDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoContacto;
    }
    
    public TipoContactoDto getTipoContactoDto(TipoContacto tipoContacto) throws IllegalAccessException, InvocationTargetException {
        TipoContactoDto tipoContactoDto = new TipoContactoDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoContactoDto, tipoContacto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoContactoDto;
    }
}
