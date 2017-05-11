package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.DepartamentoDao;
import com.minicubic.infoguiacore.dto.DepartamentoDto;
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
public class DepartamentoService {

    @Inject
    private DepartamentoDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public DepartamentoDto getDepartamento(Integer id) {
        return dao.getDepartamento(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<DepartamentoDto> getDepartamentos() {
        return dao.getDepartamentos();
    }
    
    /**
     * 
     * @param departamentoDto
     * @return 
     */
    public DepartamentoDto saveDepartamento(DepartamentoDto departamentoDto) {
        return dao.saveDepartamento(departamentoDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteDepartamento(Integer id) {
        dao.deleteDepartamento(id);
    }
}
