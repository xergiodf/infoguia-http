package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.util.converter.UsuarioConverter;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.TipoUsuario;
import com.minicubic.infoguiacore.model.Usuario;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xergio
 * @version 2
 */
public class UsuarioDao {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final UsuarioConverter converter = new UsuarioConverter();
    private static final Logger LOG = Logger.getLogger("UsuarioDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     * Obtiene un usuario en base al ID
     *
     * @param id
     * @return Registro especifico de usuario
     */
    public UsuarioDto getUsuario(Long id) {
        try {
            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            UsuarioDto usuarioDto = converter.getUsuarioDto(usuario);
            usuarioDto.setAdmin(isAdmin(usuarioDto));
            
            return usuarioDto;
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @return 
     */
    public List<UsuarioDto> getUsuarios() {
        try {
            List<Usuario> usuarios = em.createNamedQuery("Usuario.findAll")
                    .getResultList();

            return converter.getUsuariosDto(usuarios);
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

            usuario.setAuditUsuario(usuarioLogueado.getUsername());
            usuario = em.merge(usuario);
            em.flush();

            return converter.getUsuarioDto(usuario);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
        public void deleteUsuario(Long id) {
        try {
            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(usuario);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param usuario
     * @return 
     */
    private Boolean isAdmin(UsuarioDto usuario) {
        try {
            
            if( !Util.isEmpty(usuario.getTipoUsuarioDto()) ) {
                if ( Constants.DB_USR_TIPO_ADMIN_ID.equals(usuario.getTipoUsuarioDto().getId()) ) {
                    return true;
                }
            }            
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Obtiene una Lista de Tipos de Usuario
     *
     * @return Lista de Tipos de Usuarios
     */
    public List<TipoUsuario> getTiposUsuarios() {
        try {
            return em.createNamedQuery("TipoUsuario.findAll").getResultList();
        } catch (Exception ex) {
           LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
