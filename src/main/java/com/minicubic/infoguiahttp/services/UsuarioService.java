package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.UsuarioDao;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.model.TipoUsuario;
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
 * @version 2
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UsuarioService {
    
    @Inject
    private UsuarioDao dao;
    
    /**
     * Obtiene un usuario en base al ID
     * @param id
     * @return Registro especifico de usuario
     */
    public UsuarioDto getUsuario(Long id) {
        return dao.getUsuario(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<UsuarioDto> getUsuarios() {
        return dao.getUsuarios();
    }
    
    /**
     * 
     * @param usuarioDto
     * @return 
     */
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        return dao.saveUsuario(usuarioDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteUsuario(Long id) {
        dao.deleteUsuario(id);
    }
    
    
    /**
     * Obtiene una Lista de Tipos de Usuario
     * @return Lista de Tipos de Usuarios
     */
    public List<TipoUsuario> getTiposUsuarios() {
        return dao.getTiposUsuarios();
    }
}
