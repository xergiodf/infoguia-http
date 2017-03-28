package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.EstadoPublicacionDto;
import com.minicubic.infoguiacore.model.EstadoPublicacion;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class EstadoPublicacionConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    public EstadoPublicacion getEstadoPublicacion(EstadoPublicacionDto estadoPublicacionDto) throws IllegalAccessException, InvocationTargetException {
        EstadoPublicacion estadoPublicacion = new EstadoPublicacion();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(estadoPublicacion, estadoPublicacionDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return estadoPublicacion;
    }
    
    public EstadoPublicacionDto getEstadoPublicacionDto(EstadoPublicacion estadoPublicacion) throws IllegalAccessException, InvocationTargetException {
        EstadoPublicacionDto estadoPublicacionDto = new EstadoPublicacionDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(estadoPublicacionDto, estadoPublicacion);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return estadoPublicacionDto;
    }
}
