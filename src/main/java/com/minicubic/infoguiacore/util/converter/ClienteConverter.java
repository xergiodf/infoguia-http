package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.model.Cliente;
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
public class ClienteConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param clientes
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<ClienteDto> getClientesDto(List<Cliente> clientes) 
            throws IllegalAccessException, InvocationTargetException {
        List<ClienteDto> clientesDto = new ArrayList<>();
        
        for (Cliente cliente : clientes) {
            clientesDto.add(getClienteDto(cliente));
        }
        
        return clientesDto;
    }
    
    /**
     * 
     * @param clientesDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<Cliente> getClientes(List<ClienteDto> clientesDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<Cliente> clientes = new ArrayList<>();
        
        for (ClienteDto clienteDto : clientesDto) {
            clientes.add(getCliente(clienteDto));
        }
        
        return clientes;
    }
 
    /**
     * 
     * @param clienteDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public Cliente getCliente(ClienteDto clienteDto) 
            throws IllegalAccessException, InvocationTargetException {
        Cliente cliente = new Cliente();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(cliente, clienteDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Pasamos a MAYUS codigo cliente
        if ( !Util.isEmpty(cliente.getCodigoCliente()) )
            cliente.setCodigoCliente(cliente.getCodigoCliente().toUpperCase());

        return cliente;
    }
    
    /**
     * 
     * @param cliente
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public ClienteDto getClienteDto(Cliente cliente) 
            throws IllegalAccessException, InvocationTargetException {
        ClienteDto clienteDto = new ClienteDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(clienteDto, cliente);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return clienteDto;
    }
}
