package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.UsuarioDao;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.TipoUsuario;
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
     * @param usuarioDto
     * @return 
     */
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        return dao.saveUsuario(usuarioDto);
    }
    
    /**
     * Obtiene una Lista de Tipos de Usuario
     * @return Lista de Tipos de Usuarios
     */
    public List<TipoUsuario> getTiposUsuarios() {
        return dao.getTiposUsuarios();
    }
    
    /**
     * Obtiene un registro de usuario en base a sus credenciales
     * @param user
     * @param pass
     * @return 
     */
    public UsuarioDto getUsuarioByCredentials(String user, String pass) {
        return dao.getUsuarioByCredentials(user, pass);
    }
}
