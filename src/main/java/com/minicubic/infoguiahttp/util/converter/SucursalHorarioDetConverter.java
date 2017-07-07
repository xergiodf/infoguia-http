package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.SucursalHorarioDetDto;
import com.minicubic.infoguiahttp.model.SucursalHorarioDet;
import com.minicubic.infoguiahttp.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author sedf
 */
public class SucursalHorarioDetConverter {
    
    private static SucursalHorarioDetConverter instance = null;
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    private SucursalHorarioDetConverter() {}
    public static SucursalHorarioDetConverter getInstance() {
        if ( instance == null ) 
            instance = new SucursalHorarioDetConverter();
        
        return instance;
    }
    
    /**
     * 
     * @param sucursalHorarioDets
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalHorarioDetDto> getSucursalHorarioDetsDto(List<SucursalHorarioDet> sucursalHorarioDets) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalHorarioDetDto> sucursalHorarioDetsDto = new ArrayList<>();
        
        for (SucursalHorarioDet sucursalHorarioDet : sucursalHorarioDets) {
            sucursalHorarioDetsDto.add(getSucursalHorarioDetDto(sucursalHorarioDet));
        }
        
        return sucursalHorarioDetsDto;
    }
    
    /**
     * 
     * @param sucursalHorarioDetsDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalHorarioDet> getSucursalHorarioDets(List<SucursalHorarioDetDto> sucursalHorarioDetsDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalHorarioDet> sucursalHorarioDets = new ArrayList<>();
        
        for (SucursalHorarioDetDto sucursalHorarioDetDto : sucursalHorarioDetsDto) {
            sucursalHorarioDets.add(getSucursalHorarioDet(sucursalHorarioDetDto));
        }
        
        return sucursalHorarioDets;
    }
    
    public SucursalHorarioDet getSucursalHorarioDet(SucursalHorarioDetDto sucursalHorarioDetDto) throws IllegalAccessException, InvocationTargetException {
        SucursalHorarioDet sucursalHorarioDet = new SucursalHorarioDet();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalHorarioDet, sucursalHorarioDetDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(sucursalHorarioDetDto.getSucursalHorarioCabDto()) ) 
            sucursalHorarioDet.setSucursalHorarioCab(SucursalHorarioCabConverter.getInstance()
                    .getSucursalHorarioCab(sucursalHorarioDetDto.getSucursalHorarioCabDto()));
        
        return sucursalHorarioDet;
    }
    
    public SucursalHorarioDetDto getSucursalHorarioDetDto(SucursalHorarioDet sucursalHorarioDet) throws IllegalAccessException, InvocationTargetException {
        SucursalHorarioDetDto sucursalHorarioDetDto = new SucursalHorarioDetDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalHorarioDetDto, sucursalHorarioDet);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(sucursalHorarioDet.getSucursalHorarioCab()) ) 
            sucursalHorarioDetDto.setSucursalHorarioCabDto(SucursalHorarioCabConverter.getInstance()
                    .getSucursalHorarioCabDto(sucursalHorarioDet.getSucursalHorarioCab()));
        
        return sucursalHorarioDetDto;
    }
}
