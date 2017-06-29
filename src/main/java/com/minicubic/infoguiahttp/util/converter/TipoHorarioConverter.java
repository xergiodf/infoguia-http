package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.TipoHorarioDto;
import com.minicubic.infoguiahttp.model.TipoHorario;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoHorarioConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param tipoHorarios
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoHorarioDto> getTipoHorariosDto(List<TipoHorario> tipoHorarios) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoHorarioDto> tipoHorariosDto = new ArrayList<>();
        
        for (TipoHorario tipoHorario : tipoHorarios) {
            tipoHorariosDto.add(getTipoHorarioDto(tipoHorario));
        }
        
        return tipoHorariosDto;
    }
    
    /**
     * 
     * @param tipoHorariosDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoHorario> getTipoHorarios(List<TipoHorarioDto> tipoHorariosDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoHorario> tipoHorarios = new ArrayList<>();
        
        for (TipoHorarioDto tipoHorarioDto : tipoHorariosDto) {
            tipoHorarios.add(getTipoHorario(tipoHorarioDto));
        }
        
        return tipoHorarios;
    }
    
    public TipoHorario getTipoHorario(TipoHorarioDto tipoHorarioDto) throws IllegalAccessException, InvocationTargetException {
        TipoHorario tipoHorario = new TipoHorario();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoHorario, tipoHorarioDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoHorario;
    }
    
    public TipoHorarioDto getTipoHorarioDto(TipoHorario tipoHorario) throws IllegalAccessException, InvocationTargetException {
        TipoHorarioDto tipoHorarioDto = new TipoHorarioDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoHorarioDto, tipoHorario);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoHorarioDto;
    }
}
