package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.CiudadDto;
import com.minicubic.infoguiacore.model.Ciudad;
import com.minicubic.infoguiacore.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1 - 11.05.2017
 */
public class CiudadConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param ciudades
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<CiudadDto> getCiudadesDto(List<Ciudad> ciudades) 
            throws IllegalAccessException, InvocationTargetException {
        List<CiudadDto> ciudadesDto = new ArrayList<>();
        
        for (Ciudad ciudad : ciudades) {
            ciudadesDto.add(getCiudadDto(ciudad));
        }
        
        return ciudadesDto;
    }
    
    /**
     * 
     * @param ciudadesDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<Ciudad> getCiudades(List<CiudadDto> ciudadesDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<Ciudad> ciudades = new ArrayList<>();
        
        for (CiudadDto ciudadDto : ciudadesDto) {
            ciudades.add(getCiudad(ciudadDto));
        }
        
        return ciudades;
    }
 
    /**
     * 
     * @param ciudadDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public Ciudad getCiudad(CiudadDto ciudadDto) 
            throws IllegalAccessException, InvocationTargetException {
        Ciudad ciudad = new Ciudad();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(ciudad, ciudadDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(ciudadDto.getDepartamentoDto()) ) 
            ciudad.setDepartamento(new DepartamentoConverter().getDepartamento(ciudadDto.getDepartamentoDto()));
        

        return ciudad;
    }
    
    /**
     * 
     * @param ciudad
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public CiudadDto getCiudadDto(Ciudad ciudad) 
            throws IllegalAccessException, InvocationTargetException {
        CiudadDto ciudadDto = new CiudadDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(ciudadDto, ciudad);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(ciudad.getDepartamento()) )
            ciudadDto.setDepartamentoDto(new DepartamentoConverter().getDepartamentoDto(ciudad.getDepartamento()));
        
        return ciudadDto;
    }
}
