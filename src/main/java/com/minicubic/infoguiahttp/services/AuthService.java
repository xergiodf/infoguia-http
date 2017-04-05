package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.AuthDao;
import com.minicubic.infoguiacore.dto.UsuarioDto;
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
public class AuthService {
    
    @Inject
    private AuthDao dao;
    
    /**
     * 
     * @param usuarioParam
     * @return 
     */
    public UsuarioDto getUsuarioByParam(UsuarioDto usuarioParam) {
        return dao.getUsuarioByParam(usuarioParam);
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
    
    /**
     * 
     * @param usuarioDto
     * @return 
     */
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        return dao.saveUsuario(usuarioDto);
    }
}
