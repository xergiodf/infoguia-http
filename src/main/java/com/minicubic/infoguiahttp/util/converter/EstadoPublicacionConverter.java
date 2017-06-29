package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.EstadoPublicacionDto;
import com.minicubic.infoguiahttp.model.EstadoPublicacion;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class EstadoPublicacionConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param estadoPublicaciones
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<EstadoPublicacionDto> getEstadoPublicacionesDto(List<EstadoPublicacion> estadoPublicaciones) 
            throws IllegalAccessException, InvocationTargetException {
        List<EstadoPublicacionDto> estadoPublicacionsDto = new ArrayList<>();
        
        for (EstadoPublicacion estadoPublicacion : estadoPublicaciones) {
            estadoPublicacionsDto.add(getEstadoPublicacionDto(estadoPublicacion));
        }
        
        return estadoPublicacionsDto;
    }
    
    /**
     * 
     * @param estadoPublicacionesDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<EstadoPublicacion> getEstadoPublicaciones(List<EstadoPublicacionDto> estadoPublicacionesDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<EstadoPublicacion> estadoPublicacions = new ArrayList<>();
        
        for (EstadoPublicacionDto estadoPublicacionDto : estadoPublicacionesDto) {
            estadoPublicacions.add(getEstadoPublicacion(estadoPublicacionDto));
        }
        
        return estadoPublicacions;
    }
    
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
