package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.EstadoUsuarioDto;
import com.minicubic.infoguiacore.model.EstadoUsuario;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class EstadoUsuarioConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();

    public EstadoUsuario getEstadoUsuario(EstadoUsuarioDto estadoUsuarioDto) throws IllegalAccessException, InvocationTargetException {
        EstadoUsuario estadoUsuario = new EstadoUsuario();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(estadoUsuario, estadoUsuarioDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return estadoUsuario;
    }
    
    public EstadoUsuarioDto getEstadoUsuarioDto(EstadoUsuario estadoUsuario) throws IllegalAccessException, InvocationTargetException {
        EstadoUsuarioDto estadoUsuarioDto = new EstadoUsuarioDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(estadoUsuarioDto, estadoUsuario);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return estadoUsuarioDto;
    }
}
