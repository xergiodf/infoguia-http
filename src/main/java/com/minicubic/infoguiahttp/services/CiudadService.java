package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.CiudadDao;
import com.minicubic.infoguiahttp.dto.CiudadDto;
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
 * @version 1 - 11.05.2017
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CiudadService {

    @Inject
    private CiudadDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public CiudadDto getCiudad(Integer id) {
        return dao.getCiudad(id);
    }
    
    /**
     * 
     * @param idDepartamento
     * @return 
     */
    public List<CiudadDto> getCiudadesByDepartamento(Integer idDepartamento) {
        return dao.getCiudadesByDepartamento(idDepartamento);
    }
    
    /**
     * 
     * @return 
     */
    public List<CiudadDto> getCiudades() {
        return dao.getCiudades();
    }
    
    /**
     * 
     * @param ciudadDto
     * @return 
     */
    public CiudadDto saveCiudad(CiudadDto ciudadDto) {
        return dao.saveCiudad(ciudadDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteCiudad(Integer id) {
        dao.deleteCiudad(id);
    }
}
