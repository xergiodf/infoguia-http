package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.EstadoUsuarioDao;
import com.minicubic.infoguiahttp.dto.EstadoUsuarioDto;
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
 * @version 1 - 27.04.2017
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EstadoUsuarioService {

    @Inject
    private EstadoUsuarioDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public EstadoUsuarioDto getEstadoUsuario(Integer id) {
        return dao.getEstadoUsuario(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<EstadoUsuarioDto> getEstadoUsuarios() {
        return dao.getEstadoUsuarios();
    }
    
    /**
     * 
     * @param estadoUsuarioDto
     * @return 
     */
    public EstadoUsuarioDto saveEstadoUsuario(EstadoUsuarioDto estadoUsuarioDto) {
        return dao.saveEstadoUsuario(estadoUsuarioDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteEstadoUsuario(Integer id) {
        dao.deleteEstadoUsuario(id);
    }
}
