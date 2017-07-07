package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.SucursalHorarioCabDto;
import com.minicubic.infoguiahttp.model.SucursalHorarioCab;
import com.minicubic.infoguiahttp.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author sedf
 */
public class SucursalHorarioCabConverter {
    
    private static SucursalHorarioCabConverter instance = null;
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    private SucursalHorarioCabConverter() {}
    public static SucursalHorarioCabConverter getInstance() {
        if ( instance == null ) 
            instance = new SucursalHorarioCabConverter();
        
        return instance;
    }
    
    /**
     * 
     * @param sucursalHorarioCabs
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalHorarioCabDto> getSucursalHorarioCabsDto(List<SucursalHorarioCab> sucursalHorarioCabs) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalHorarioCabDto> sucursalHorarioCabsDto = new ArrayList<>();
        
        for (SucursalHorarioCab sucursalHorarioCab : sucursalHorarioCabs) {
            sucursalHorarioCabsDto.add(getSucursalHorarioCabDto(sucursalHorarioCab));
        }
        
        return sucursalHorarioCabsDto;
    }
    
    /**
     * 
     * @param sucursalHorarioCabsDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalHorarioCab> getSucursalHorarioCabs(List<SucursalHorarioCabDto> sucursalHorarioCabsDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalHorarioCab> sucursalHorarioCabs = new ArrayList<>();
        
        for (SucursalHorarioCabDto sucursalHorarioCabDto : sucursalHorarioCabsDto) {
            sucursalHorarioCabs.add(getSucursalHorarioCab(sucursalHorarioCabDto));
        }
        
        return sucursalHorarioCabs;
    }
    
    public SucursalHorarioCab getSucursalHorarioCab(SucursalHorarioCabDto sucursalHorarioCabDto) throws IllegalAccessException, InvocationTargetException {
        SucursalHorarioCab sucursalHorarioCab = new SucursalHorarioCab();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalHorarioCab, sucursalHorarioCabDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(sucursalHorarioCabDto.getTipoHorarioDto()) ) 
            sucursalHorarioCab.setTipoHorario(new TipoHorarioConverter()
                    .getTipoHorario(sucursalHorarioCabDto.getTipoHorarioDto()));
        
        if ( !Util.isEmpty(sucursalHorarioCabDto.getClienteSucursalDto()) )
            sucursalHorarioCab.setClienteSucursal(new ClienteSucursalConverter()
                    .getClienteSucursal(sucursalHorarioCabDto.getClienteSucursalDto()));
        
        return sucursalHorarioCab;
    }
    
    public SucursalHorarioCabDto getSucursalHorarioCabDto(SucursalHorarioCab sucursalHorarioCab) throws IllegalAccessException, InvocationTargetException {
        SucursalHorarioCabDto sucursalHorarioCabDto = new SucursalHorarioCabDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalHorarioCabDto, sucursalHorarioCab);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(sucursalHorarioCab.getTipoHorario()) ) 
            sucursalHorarioCabDto.setTipoHorarioDto(new TipoHorarioConverter()
                    .getTipoHorarioDto(sucursalHorarioCab.getTipoHorario()));
        
        if ( !Util.isEmpty(sucursalHorarioCab.getClienteSucursal()) )
            sucursalHorarioCabDto.setClienteSucursalDto(new ClienteSucursalConverter()
                    .getClienteSucursalDto(sucursalHorarioCab.getClienteSucursal()));
        
        return sucursalHorarioCabDto;
    }
}
