package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.TipoArchivoDto;
import com.minicubic.infoguiacore.model.TipoArchivo;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoArchivoConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    public TipoArchivo getTipoArchivo(TipoArchivoDto tipoArchivoDto) throws IllegalAccessException, InvocationTargetException {
        TipoArchivo tipoArchivo = new TipoArchivo();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoArchivo, tipoArchivoDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoArchivo;
    }
    
    public TipoArchivoDto getTipoArchivoDto(TipoArchivo tipoArchivo) throws IllegalAccessException, InvocationTargetException {
        TipoArchivoDto tipoArchivoDto = new TipoArchivoDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoArchivoDto, tipoArchivo);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoArchivoDto;
    }
}
