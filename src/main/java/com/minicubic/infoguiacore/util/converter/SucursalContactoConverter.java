package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.SucursalContactoDto;
import com.minicubic.infoguiacore.model.SucursalContacto;
import com.minicubic.infoguiacore.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class SucursalContactoConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param sucursalContactos
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalContactoDto> getSucursalContactosDto(List<SucursalContacto> sucursalContactos) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalContactoDto> sucursalContactosDto = new ArrayList<>();
        
        for (SucursalContacto sucursalContacto : sucursalContactos) {
            sucursalContactosDto.add(getSucursalContactoDto(sucursalContacto));
        }
        
        return sucursalContactosDto;
    }
    
    /**
     * 
     * @param sucursalContactosDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalContacto> getSucursalContactoes(List<SucursalContactoDto> sucursalContactosDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalContacto> sucursalContactos = new ArrayList<>();
        
        for (SucursalContactoDto sucursalContactoDto : sucursalContactosDto) {
            sucursalContactos.add(getSucursalContacto(sucursalContactoDto));
        }
        
        return sucursalContactos;
    }
 
    /**
     * 
     * @param sucursalContactoDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public SucursalContacto getSucursalContacto(SucursalContactoDto sucursalContactoDto) 
            throws IllegalAccessException, InvocationTargetException {
        SucursalContacto sucursalContacto = new SucursalContacto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalContacto, sucursalContactoDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas complejas
        if ( !Util.isEmpty(sucursalContactoDto.getClienteSucursalDto())) 
            sucursalContacto.setClienteSucursal(new ClienteSucursalConverter()
                    .getClienteSucursal(sucursalContactoDto.getClienteSucursalDto()));
        
        if ( !Util.isEmpty(sucursalContactoDto.getTipoContactoDto())) 
            sucursalContacto.setTipoContacto(new TipoContactoConverter()
                    .getTipoContacto(sucursalContactoDto.getTipoContactoDto()));
        

        return sucursalContacto;
    }
    
    /**
     * 
     * @param sucursalContacto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public SucursalContactoDto getSucursalContactoDto(SucursalContacto sucursalContacto) 
            throws IllegalAccessException, InvocationTargetException {
        SucursalContactoDto sucursalContactoDto = new SucursalContactoDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalContactoDto, sucursalContacto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas complejas
        if ( !Util.isEmpty(sucursalContacto.getClienteSucursal())) 
            sucursalContactoDto.setClienteSucursalDto(new ClienteSucursalConverter()
                    .getClienteSucursalDto(sucursalContacto.getClienteSucursal()));
        
        if ( !Util.isEmpty(sucursalContacto.getTipoContacto())) 
            sucursalContactoDto.setTipoContactoDto(new TipoContactoConverter()
                    .getTipoContactoDto(sucursalContacto.getTipoContacto()));
        
        return sucursalContactoDto;
    }
}
