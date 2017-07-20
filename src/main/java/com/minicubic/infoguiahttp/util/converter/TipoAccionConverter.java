package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.TipoAccionDto;
import com.minicubic.infoguiahttp.model.TipoAccion;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoAccionConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param tipoAccions
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoAccionDto> getTipoAccionsDto(List<TipoAccion> tipoAccions) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoAccionDto> tipoAccionsDto = new ArrayList<>();
        
        for (TipoAccion tipoAccion : tipoAccions) {
            tipoAccionsDto.add(getTipoAccionDto(tipoAccion));
        }
        
        return tipoAccionsDto;
    }
    
    /**
     * 
     * @param tipoAccionsDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoAccion> getTipoAccions(List<TipoAccionDto> tipoAccionsDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoAccion> tipoAccions = new ArrayList<>();
        
        for (TipoAccionDto tipoAccionDto : tipoAccionsDto) {
            tipoAccions.add(getTipoAccion(tipoAccionDto));
        }
        
        return tipoAccions;
    }
    
    public TipoAccion getTipoAccion(TipoAccionDto tipoAccionDto) throws IllegalAccessException, InvocationTargetException {
        TipoAccion tipoAccion = new TipoAccion();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoAccion, tipoAccionDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoAccion;
    }
    
    public TipoAccionDto getTipoAccionDto(TipoAccion tipoAccion) throws IllegalAccessException, InvocationTargetException {
        TipoAccionDto tipoAccionDto = new TipoAccionDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoAccionDto, tipoAccion);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoAccionDto;
    }
}
