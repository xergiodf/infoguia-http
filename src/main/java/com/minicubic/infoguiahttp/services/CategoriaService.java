package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.CategoriaDao;
import com.minicubic.infoguiacore.dto.CategoriaDto;
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
public class CategoriaService {

    @Inject
    private CategoriaDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public CategoriaDto getCategoria(Integer id) {
        return dao.getCategoria(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<CategoriaDto> getCategorias() {
        return dao.getCategorias();
    }
    
    /**
     * 
     * @param categoriaDto
     * @return 
     */
    public CategoriaDto saveCategoria(CategoriaDto categoriaDto) {
        return dao.saveCategoria(categoriaDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteCategoria(Long id) {
        dao.deleteCategoria(id);
    }
}
