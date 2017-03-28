package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.SucursalContactoDao;
import com.minicubic.infoguiacore.dto.SucursalContactoDto;
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
public class SucursalContactoService {

    @Inject
    private SucursalContactoDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public SucursalContactoDto getSucursalContacto(Integer id) {
        return dao.getSucursalContacto(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<SucursalContactoDto> getSucursalContactoes() {
        return dao.getSucursalContactoes();
    }
    
    /**
     * 
     * @param sucursalContactoDto
     * @return 
     */
    public SucursalContactoDto saveSucursalContacto(SucursalContactoDto sucursalContactoDto) {
        return dao.saveSucursalContacto(sucursalContactoDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteSucursalContacto(Integer id) {
        dao.deleteSucursalContacto(id);
    }
}
