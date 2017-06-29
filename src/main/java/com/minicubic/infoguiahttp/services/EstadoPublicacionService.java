package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.EstadoPublicacionDao;
import com.minicubic.infoguiahttp.dto.EstadoPublicacionDto;
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
public class EstadoPublicacionService {

    @Inject
    private EstadoPublicacionDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public EstadoPublicacionDto getEstadoPublicacion(Integer id) {
        return dao.getEstadoPublicacion(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<EstadoPublicacionDto> getEstadoPublicaciones() {
        return dao.getEstadoPublicaciones();
    }
    
    /**
     * 
     * @param estadoPublicacionDto
     * @return 
     */
    public EstadoPublicacionDto saveEstadoPublicacion(EstadoPublicacionDto estadoPublicacionDto) {
        return dao.saveEstadoPublicacion(estadoPublicacionDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteEstadoPublicacion(Integer id) {
        dao.deleteEstadoPublicacion(id);
    }
}
