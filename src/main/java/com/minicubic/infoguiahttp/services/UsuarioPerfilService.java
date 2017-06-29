package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.UsuarioPerfilDao;
import com.minicubic.infoguiahttp.dto.UsuarioPerfilDto;
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
public class UsuarioPerfilService {
    
    @Inject
    UsuarioPerfilDao dao;
    
    /**
     *
     * @param id
     * @return
     */
    public UsuarioPerfilDto getUsuarioPerfil(Integer id) {
        return dao.getUsuarioPerfil(id);
    }
    
    /**
     * 
     * @param idUsuario
     * @return 
     */
    public UsuarioPerfilDto getUsuarioPerfilByUsuario(Long idUsuario) {
        return dao.getUsuarioPerfilByUsuario(idUsuario);
    }
    
    /**
     * 
     * @return 
     */
    public List<UsuarioPerfilDto> getUsuarioPerfiles() {
        return dao.getUsuarioPerfiles();
    }
    
    /**
     *
     * @param usuarioPerfilDto
     * @return
     */
    public UsuarioPerfilDto saveUsuarioPerfil(UsuarioPerfilDto usuarioPerfilDto) {
        return dao.saveUsuarioPerfil(usuarioPerfilDto);
    }

    /**
     * 
     * @param id 
     */
    public void deleteUsuarioPerfil(Integer id) {
        dao.deleteUsuarioPerfil(id);
    }
}
