package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.SucursalValoracionCabDto;
import com.minicubic.infoguiahttp.dto.SucursalValoracionDetDto;
import com.minicubic.infoguiahttp.model.SucursalValoracionCab;
import com.minicubic.infoguiahttp.model.SucursalValoracionDet;
import com.minicubic.infoguiahttp.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1 - 12.06.2017
 */
public class SucursalValoracionConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();

    /**
     * 
     * @param sucursalValoracionesCab
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalValoracionCabDto> getSucursalValoracionesCabDto(List<SucursalValoracionCab> sucursalValoracionesCab) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalValoracionCabDto> sucursalValoracionesCabDto = new ArrayList<>();
        
        for (SucursalValoracionCab sucursalValoracionCab : sucursalValoracionesCab) {
            sucursalValoracionesCabDto.add(getSucursalValoracionCabDto(sucursalValoracionCab));
        }
        
        return sucursalValoracionesCabDto;
    }
    
    /**
     * 
     * @param sucursalValoracionesCabDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalValoracionCab> getSucursalValoracionesCab(List<SucursalValoracionCabDto> sucursalValoracionesCabDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalValoracionCab> sucursalValoracionesCab = new ArrayList<>();
        
        for (SucursalValoracionCabDto sucursalValoracionCabDto : sucursalValoracionesCabDto) {
            sucursalValoracionesCab.add(getSucursalValoracionCab(sucursalValoracionCabDto));
        }
        
        return sucursalValoracionesCab;
    }
 
    /**
     * 
     * @param sucursalValoracionCabDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public SucursalValoracionCab getSucursalValoracionCab(SucursalValoracionCabDto sucursalValoracionCabDto) 
            throws IllegalAccessException, InvocationTargetException {
        SucursalValoracionCab sucursalValoracionCab = new SucursalValoracionCab();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalValoracionCab, sucursalValoracionCabDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(sucursalValoracionCabDto.getClienteSucursalDto()) )
            sucursalValoracionCab.setClienteSucursal(new ClienteSucursalConverter().getClienteSucursal(sucursalValoracionCabDto.getClienteSucursalDto()));
        
        if ( !Util.isEmpty(sucursalValoracionCabDto.getSucursalValoracionesDetDto()) ) {
            if ( !sucursalValoracionCabDto.getSucursalValoracionesDetDto().isEmpty() )
                sucursalValoracionCab.setSucursalValoracionesDet(getSucursalValoracionesDet(sucursalValoracionCabDto.getSucursalValoracionesDetDto(), sucursalValoracionCab));
        }

        return sucursalValoracionCab;
    }
    
    /**
     * 
     * @param sucursalValoracionCab
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public SucursalValoracionCabDto getSucursalValoracionCabDto(SucursalValoracionCab sucursalValoracionCab) 
            throws IllegalAccessException, InvocationTargetException {
        SucursalValoracionCabDto sucursalValoracionCabDto = new SucursalValoracionCabDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalValoracionCabDto, sucursalValoracionCab);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(sucursalValoracionCab.getClienteSucursal()) )
            sucursalValoracionCabDto.setClienteSucursalDto(new ClienteSucursalConverter().getClienteSucursalDto(sucursalValoracionCab.getClienteSucursal()));
        
        if ( !Util.isEmpty(sucursalValoracionCab.getSucursalValoracionesDet()) ) {
            if ( !sucursalValoracionCab.getSucursalValoracionesDet().isEmpty() ) 
                sucursalValoracionCabDto.setSucursalValoracionesDetDto(getSucursalValoracionesDetDto(sucursalValoracionCab.getSucursalValoracionesDet()));
        }
        
        return sucursalValoracionCabDto;
    }
    
    /**
     * 
     * @param sucursalValoracionesDet
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalValoracionDetDto> getSucursalValoracionesDetDto(List<SucursalValoracionDet> sucursalValoracionesDet) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalValoracionDetDto> sucursalValoracionesDetDto = new ArrayList<>();
        
        for (SucursalValoracionDet sucursalValoracionDet : sucursalValoracionesDet) {
            sucursalValoracionesDetDto.add(getSucursalValoracionDetDto(sucursalValoracionDet));
        }
        
        return sucursalValoracionesDetDto;
    }
    
    /**
     * 
     * @param sucursalValoracionesDetDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<SucursalValoracionDet> getSucursalValoracionesDet(List<SucursalValoracionDetDto> sucursalValoracionesDetDto, SucursalValoracionCab sucursalValoracionCab) 
            throws IllegalAccessException, InvocationTargetException {
        List<SucursalValoracionDet> sucursalValoracionesDet = new ArrayList<>();
        
        for (SucursalValoracionDetDto sucursalValoracionDetDto : sucursalValoracionesDetDto) {
            sucursalValoracionesDet.add(getSucursalValoracionDet(sucursalValoracionDetDto, sucursalValoracionCab));
        }
        
        return sucursalValoracionesDet;
    }
    
    /**
     * 
     * @param sucursalValoracionDetDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public SucursalValoracionDet getSucursalValoracionDet(SucursalValoracionDetDto sucursalValoracionDetDto, SucursalValoracionCab sucursalValoracionCab) 
            throws IllegalAccessException, InvocationTargetException {
        SucursalValoracionDet sucursalValoracionDet = new SucursalValoracionDet();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalValoracionDet, sucursalValoracionDetDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(sucursalValoracionDetDto.getUsuarioDto()) )
            sucursalValoracionDet.setUsuario(new UsuarioConverter().getUsuario(sucursalValoracionDetDto.getUsuarioDto()));
        
        sucursalValoracionDet.setSucursalValoracionCab(sucursalValoracionCab);
        
        return sucursalValoracionDet;
    }
    
    /**
     * 
     * @param sucursalValoracionDet
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public SucursalValoracionDetDto getSucursalValoracionDetDto(SucursalValoracionDet sucursalValoracionDet) 
            throws IllegalAccessException, InvocationTargetException {
        SucursalValoracionDetDto sucursalValoracionDetDto = new SucursalValoracionDetDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(sucursalValoracionDetDto, sucursalValoracionDet);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(sucursalValoracionDet.getUsuario()) )
            sucursalValoracionDetDto.setUsuarioDto(new UsuarioConverter().getUsuarioDto(sucursalValoracionDet.getUsuario()));
        
        return sucursalValoracionDetDto;
    }
    
}
