package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.TipoUsuarioDao;
import com.minicubic.infoguiahttp.dto.TipoUsuarioDto;
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
public class TipoUsuarioService {

    @Inject
    private TipoUsuarioDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public TipoUsuarioDto getTipoUsuario(Integer id) {
        return dao.getTipoUsuario(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<TipoUsuarioDto> getTipoUsuarios() {
        return dao.getTipoUsuarios();
    }
    
    /**
     * 
     * @param tipoUsuarioDto
     * @return 
     */
    public TipoUsuarioDto saveTipoUsuario(TipoUsuarioDto tipoUsuarioDto) {
        return dao.saveTipoUsuario(tipoUsuarioDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteTipoUsuario(Integer id) {
        dao.deleteTipoUsuario(id);
    }
}
