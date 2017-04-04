package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.Usuario;
import com.minicubic.infoguiacore.util.converter.UsuarioConverter;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xergio
 * @version 1
 */
public class AuthDao {

    private final UsuarioConverter converter = new UsuarioConverter();
    private static final Logger LOG = Logger.getLogger("UsuarioDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    /**
     *
     * @param user
     * @param pass
     * @return
     */
    public UsuarioDto getUsuarioByCredentials(String user, String pass) {
        try {

            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByUsernameAndPassword")
                    .setParameter("username", user)
                    .setParameter("password", pass)
                    .getSingleResult();

            return converter.getUsuarioDto(usuario);

        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param usuarioDto
     * @return
     */
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        try {
            Usuario usuario = converter.getUsuario(usuarioDto);

            usuario.setAuditUsuario(usuarioDto.getUsername());
            usuario = em.merge(usuario);
            em.flush();

            return converter.getUsuarioDto(usuario);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
