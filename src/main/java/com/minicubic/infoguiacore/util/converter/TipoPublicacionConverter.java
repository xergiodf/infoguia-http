package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.TipoPublicacionDto;
import com.minicubic.infoguiacore.model.TipoPublicacion;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoPublicacionConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    public TipoPublicacion getTipoPublicacion(TipoPublicacionDto tipoPublicacionDto) throws IllegalAccessException, InvocationTargetException {
        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoPublicacion, tipoPublicacionDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoPublicacion;
    }
    
    public TipoPublicacionDto getTipoPublicacionDto(TipoPublicacion tipoPublicacion) throws IllegalAccessException, InvocationTargetException {
        TipoPublicacionDto tipoPublicacionDto = new TipoPublicacionDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoPublicacionDto, tipoPublicacion);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoPublicacionDto;
    }
}
