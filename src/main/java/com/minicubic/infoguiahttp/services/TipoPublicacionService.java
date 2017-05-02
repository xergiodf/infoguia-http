package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.TipoPublicacionDao;
import com.minicubic.infoguiacore.dto.TipoPublicacionDto;
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
public class TipoPublicacionService {

    @Inject
    private TipoPublicacionDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public TipoPublicacionDto getTipoPublicacion(Integer id) {
        return dao.getTipoPublicacion(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<TipoPublicacionDto> getTipoPublicaciones() {
        return dao.getTipoPublicaciones();
    }
    
    /**
     * 
     * @param tipoPublicacionDto
     * @return 
     */
    public TipoPublicacionDto saveTipoPublicacion(TipoPublicacionDto tipoPublicacionDto) {
        return dao.saveTipoPublicacion(tipoPublicacionDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteTipoPublicacion(Integer id) {
        dao.deleteTipoPublicacion(id);
    }
}
