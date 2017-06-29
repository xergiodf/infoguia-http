package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.ClienteCategoriaDao;
import com.minicubic.infoguiahttp.dto.ClienteCategoriaDto;
import com.minicubic.infoguiahttp.model.ClienteCategoriaPK;
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
public class ClienteCategoriaService {
    
    @Inject
    private ClienteCategoriaDao dao;
    
    /**
     *
     * @param idCliente
     * @return
     */
    public List<ClienteCategoriaDto> getClienteCategoriasByCliente(Long idCliente) {
        return dao.getClienteCategoriasByCliente(idCliente);
    }
    
    /**
     * 
     * @param idCategoria
     * @return 
     */
    public List<ClienteCategoriaDto> getClienteCategoriasByCategoria(Integer idCategoria) {
        return dao.getClienteCategoriasByCategoria(idCategoria);
    }
    
    /**
     *
     * @param clienteCategoriaDto
     * @return
     */
    public ClienteCategoriaDto saveClienteCategoria(ClienteCategoriaDto clienteCategoriaDto) {
        return dao.saveClienteCategoria(clienteCategoriaDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteClienteCategoria(ClienteCategoriaPK id) {
        dao.deleteClienteCategoria(id);
    }
}
