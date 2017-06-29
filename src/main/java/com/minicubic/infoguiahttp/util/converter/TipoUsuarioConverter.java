package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.TipoUsuarioDto;
import com.minicubic.infoguiahttp.model.TipoUsuario;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class TipoUsuarioConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param tipoUsuarios
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoUsuarioDto> getTipoUsuariosDto(List<TipoUsuario> tipoUsuarios) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoUsuarioDto> tipoUsuariosDto = new ArrayList<>();
        
        for (TipoUsuario tipoUsuario : tipoUsuarios) {
            tipoUsuariosDto.add(getTipoUsuarioDto(tipoUsuario));
        }
        
        return tipoUsuariosDto;
    }
    
    /**
     * 
     * @param tipoUsuariosDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<TipoUsuario> getTipoUsuarios(List<TipoUsuarioDto> tipoUsuariosDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<TipoUsuario> tipoUsuarios = new ArrayList<>();
        
        for (TipoUsuarioDto tipoUsuarioDto : tipoUsuariosDto) {
            tipoUsuarios.add(getTipoUsuario(tipoUsuarioDto));
        }
        
        return tipoUsuarios;
    }
    
    public TipoUsuario getTipoUsuario(TipoUsuarioDto tipoUsuarioDto) throws IllegalAccessException, InvocationTargetException {
        TipoUsuario tipoUsuario = new TipoUsuario();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoUsuario, tipoUsuarioDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoUsuario;
    }
    
    public TipoUsuarioDto getTipoUsuarioDto(TipoUsuario tipoUsuario) throws IllegalAccessException, InvocationTargetException {
        TipoUsuarioDto tipoUsuarioDto = new TipoUsuarioDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(tipoUsuarioDto, tipoUsuario);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return tipoUsuarioDto;
    }
}
