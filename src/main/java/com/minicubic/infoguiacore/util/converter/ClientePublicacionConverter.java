package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.ClientePublicacionDto;
import com.minicubic.infoguiacore.model.ClientePublicacion;
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
public class ClientePublicacionConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param clientePublicacions
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<ClientePublicacionDto> getClientePublicacionesDto(List<ClientePublicacion> clientePublicacions) 
            throws IllegalAccessException, InvocationTargetException {
        List<ClientePublicacionDto> clientePublicacionsDto = new ArrayList<>();
        
        for (ClientePublicacion clientePublicacion : clientePublicacions) {
            clientePublicacionsDto.add(getClientePublicacionDto(clientePublicacion));
        }
        
        return clientePublicacionsDto;
    }
    
    /**
     * 
     * @param clientePublicacionsDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<ClientePublicacion> getClientePublicaciones(List<ClientePublicacionDto> clientePublicacionsDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<ClientePublicacion> clientePublicacions = new ArrayList<>();
        
        for (ClientePublicacionDto clientePublicacionDto : clientePublicacionsDto) {
            clientePublicacions.add(getClientePublicacion(clientePublicacionDto));
        }
        
        return clientePublicacions;
    }
 
    /**
     * 
     * @param clientePublicacionDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public ClientePublicacion getClientePublicacion(ClientePublicacionDto clientePublicacionDto) 
            throws IllegalAccessException, InvocationTargetException {
        ClientePublicacion clientePublicacion = new ClientePublicacion();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(clientePublicacion, clientePublicacionDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(clientePublicacionDto.getTipoPublicacionDto()) ) 
            clientePublicacion.setTipoPublicacion(new TipoPublicacionConverter()
                    .getTipoPublicacion(clientePublicacionDto.getTipoPublicacionDto()));
        
        if ( !Util.isEmpty(clientePublicacionDto.getEstadoPublicacionDto()) ) 
            clientePublicacion.setEstadoPublicacion(new EstadoPublicacionConverter()
                    .getEstadoPublicacion(clientePublicacionDto.getEstadoPublicacionDto()));
        
        if ( !Util.isEmpty(clientePublicacionDto.getClienteDto())) 
            clientePublicacion.setCliente(new ClienteConverter()
                    .getCliente(clientePublicacionDto.getClienteDto()));
        
        return clientePublicacion;
    }
    
    /**
     * 
     * @param clientePublicacion
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public ClientePublicacionDto getClientePublicacionDto(ClientePublicacion clientePublicacion) 
            throws IllegalAccessException, InvocationTargetException {
        ClientePublicacionDto clientePublicacionDto = new ClientePublicacionDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(clientePublicacionDto, clientePublicacion);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas compuestas
        if ( !Util.isEmpty(clientePublicacion.getTipoPublicacion()) ) 
            clientePublicacionDto.setTipoPublicacionDto(new TipoPublicacionConverter()
                    .getTipoPublicacionDto(clientePublicacion.getTipoPublicacion()));
        
        if ( !Util.isEmpty(clientePublicacion.getEstadoPublicacion()) ) 
            clientePublicacionDto.setEstadoPublicacionDto(new EstadoPublicacionConverter()
                    .getEstadoPublicacionDto(clientePublicacion.getEstadoPublicacion()));
        
        if ( !Util.isEmpty(clientePublicacion.getCliente())) 
            clientePublicacionDto.setClienteDto(new ClienteConverter()
                    .getClienteDto(clientePublicacion.getCliente()));
        
        return clientePublicacionDto;
    }
}
