package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.TipoPublicacionDto;
import com.minicubic.infoguiahttp.model.TipoPublicacion;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoPublicacionConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param tipoPublicaciones
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoPublicacionDto> getTipoPublicacionesDto(List<TipoPublicacion> tipoPublicaciones) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoPublicacionDto> tipoPublicacionsDto = new ArrayList<>();
        
        for (TipoPublicacion tipoPublicacion : tipoPublicaciones) {
            tipoPublicacionsDto.add(getTipoPublicacionDto(tipoPublicacion));
        }
        
        return tipoPublicacionsDto;
    }
    
    /**
     * 
     * @param tipoPublicacionesDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoPublicacion> getTipoPublicaciones(List<TipoPublicacionDto> tipoPublicacionesDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoPublicacion> tipoPublicacions = new ArrayList<>();
        
        for (TipoPublicacionDto tipoPublicacionDto : tipoPublicacionesDto) {
            tipoPublicacions.add(getTipoPublicacion(tipoPublicacionDto));
        }
        
        return tipoPublicacions;
    }
    
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
