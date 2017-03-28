package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.ClienteSucursalDto;
import com.minicubic.infoguiacore.model.ClienteSucursal;
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
public class ClienteSucursalConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param clienteSucursales
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<ClienteSucursalDto> getClienteSucursalesDto(List<ClienteSucursal> clienteSucursales) 
            throws IllegalAccessException, InvocationTargetException {
        List<ClienteSucursalDto> clienteSucursalesDto = new ArrayList<>();
        
        for (ClienteSucursal clienteSucursal : clienteSucursales) {
            clienteSucursalesDto.add(getClienteSucursalDto(clienteSucursal));
        }
        
        return clienteSucursalesDto;
    }
    
    /**
     * 
     * @param clienteSucursalesDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<ClienteSucursal> getClienteSucursales(List<ClienteSucursalDto> clienteSucursalesDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<ClienteSucursal> clienteSucursales = new ArrayList<>();
        
        for (ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto) {
            clienteSucursales.add(getClienteSucursal(clienteSucursalDto));
        }
        
        return clienteSucursales;
    }
 
    /**
     * 
     * @param clienteSucursalDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public ClienteSucursal getClienteSucursal(ClienteSucursalDto clienteSucursalDto) 
            throws IllegalAccessException, InvocationTargetException {
        ClienteSucursal clienteSucursal = new ClienteSucursal();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(clienteSucursal, clienteSucursalDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas complejas
        if ( !Util.isEmpty(clienteSucursalDto.getClienteDto())) 
            clienteSucursal.setCliente(new ClienteConverter()
                    .getCliente(clienteSucursalDto.getClienteDto()));
        

        return clienteSucursal;
    }
    
    /**
     * 
     * @param clienteSucursal
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public ClienteSucursalDto getClienteSucursalDto(ClienteSucursal clienteSucursal) 
            throws IllegalAccessException, InvocationTargetException {
        ClienteSucursalDto clienteSucursalDto = new ClienteSucursalDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(clienteSucursalDto, clienteSucursal);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedas complejas
        if ( !Util.isEmpty(clienteSucursal.getCliente())) 
            clienteSucursalDto.setClienteDto(new ClienteConverter()
                    .getClienteDto(clienteSucursal.getCliente()));
        
        return clienteSucursalDto;
    }
}
