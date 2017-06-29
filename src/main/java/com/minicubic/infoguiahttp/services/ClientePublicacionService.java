package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.ClientePublicacionDao;
import com.minicubic.infoguiahttp.dto.ClientePublicacionDto;
import com.minicubic.infoguiahttp.util.Constants;
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
public class ClientePublicacionService {

    @Inject
    private ClientePublicacionDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public ClientePublicacionDto getClientePublicacion(Integer id) {
        return dao.getClientePublicacion(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicaciones() {
        return dao.getClientePublicaciones();
    }
    
    /**
     * 
     * @param tipoPublicacionId
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicacionesByTipoPublicacion(Integer tipoPublicacionId) {
        return dao.getClientePublicacionesByTipoPublicacion(tipoPublicacionId);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicacionesByCliente(Long id) {
        return dao.getClientePublicacionesByCliente(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<ClientePublicacionDto> getNovedades() {
        return dao.getClientePublicacionesByTipoPublicacion(Constants.DB_PUB_TIPO_NOVED_ID);
    }
    
    /**
     * 
     * @return 
     */
    public List<ClientePublicacionDto> getPromociones() {
        return dao.getClientePublicacionesByTipoPublicacion(Constants.DB_PUB_TIPO_PROMO_ID);
    }
    
    /**
     * 
     * @param clientePublicacionDto
     * @return 
     */
    public ClientePublicacionDto saveClientePublicacion(ClientePublicacionDto clientePublicacionDto) {
        return dao.saveClientePublicacion(clientePublicacionDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteClientePublicacion(Integer id) {
        dao.deleteClientePublicacion(id);
    }
}
