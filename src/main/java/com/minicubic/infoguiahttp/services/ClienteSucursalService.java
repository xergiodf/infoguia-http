package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.ClienteSucursalDao;
import com.minicubic.infoguiacore.dto.ClienteSucursalDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

/**
 *
 * @author xergio
 * @version 1
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClienteSucursalService {

    @Inject
    private ClienteSucursalDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public ClienteSucursalDto getClienteSucursal(Integer id) {
        return dao.getClienteSucursal(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<ClienteSucursalDto> getClienteSucursales() {
        return dao.getClienteSucursales();
    }
    
    
    /**
     * 
     * @param clienteId
     * @return 
     */
    public List<ClienteSucursalDto> getClienteSucursalesByCliente(Long clienteId) {
        return dao.getClienteSucursalesByCliente(clienteId);
    }
    
    /**
     * 
     * @param clienteSucursalDto
     * @return 
     */
    public ClienteSucursalDto saveClienteSucursal(ClienteSucursalDto clienteSucursalDto) {
        return dao.saveClienteSucursal(clienteSucursalDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteClienteSucursal(Integer id) {
        dao.deleteClienteSucursal(id);
    }
}
