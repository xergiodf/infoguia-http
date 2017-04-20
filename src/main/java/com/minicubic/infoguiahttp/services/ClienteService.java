package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.ClienteDao;
import com.minicubic.infoguiacore.dto.ClienteDto;
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
public class ClienteService {
    
    @Inject
    private ClienteDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public ClienteDto getCliente(Long id) {
        return dao.getCliente(id);
    }
    
    /**
     * 
     * @param clienteDto
     * @return 
     */
    public ClienteDto getClienteByParam(ClienteDto clienteDto) {
        return dao.getClienteByParam(clienteDto);
    }
    
    /**
     * 
     * @return 
     */
    public List<ClienteDto> getClientes() {
        return dao.getClientes();
    }
    
    /**
     * 
     * @param clienteDto
     * @return 
     */
    public ClienteDto saveCliente(ClienteDto clienteDto) {
        return dao.saveCliente(clienteDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteCliente(Long id) {
        dao.deleteCliente(id);
    }
}
