package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.EstadoUsuarioDto;
import com.minicubic.infoguiahttp.model.EstadoUsuario;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class EstadoUsuarioConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param estadoUsuarios
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<EstadoUsuarioDto> getEstadoUsuariosDto(List<EstadoUsuario> estadoUsuarios) 
            throws IllegalAccessException, InvocationTargetException {
        List<EstadoUsuarioDto> estadoUsuariosDto = new ArrayList<>();
        
        for (EstadoUsuario estadoUsuario : estadoUsuarios) {
            estadoUsuariosDto.add(getEstadoUsuarioDto(estadoUsuario));
        }
        
        return estadoUsuariosDto;
    }
    
    /**
     * 
     * @param estadoUsuariosDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<EstadoUsuario> getEstadoUsuarios(List<EstadoUsuarioDto> estadoUsuariosDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<EstadoUsuario> estadoUsuarios = new ArrayList<>();
        
        for (EstadoUsuarioDto estadoUsuarioDto : estadoUsuariosDto) {
            estadoUsuarios.add(getEstadoUsuario(estadoUsuarioDto));
        }
        
        return estadoUsuarios;
    }

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
