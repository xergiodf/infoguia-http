package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.UsuarioPerfilDto;
import com.minicubic.infoguiacore.model.Usuario;
import com.minicubic.infoguiacore.model.UsuarioPerfil;
import com.minicubic.infoguiacore.util.converter.UsuarioPerfilConverter;
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
 * @version 1
 */
public class UsuarioPerfilDao {
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final UsuarioPerfilConverter converter = new UsuarioPerfilConverter();
    private static final Logger LOG = Logger.getLogger("UsuarioDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public UsuarioPerfilDto getUsuarioPerfil(Integer id) {
        try {
            UsuarioPerfil usuarioPerfil = (UsuarioPerfil) em.createNamedQuery("UsuarioPerfil.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getUsuarioPerfilDto(usuarioPerfil);
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
    public List<UsuarioPerfilDto> getUsuarioPerfiles() {
        try {
            List<UsuarioPerfil> usuarioPerfiles = em.createNamedQuery("UsuarioPerfil.findAll")
                    .getResultList();

            return converter.getUsuarioPerfilesDto(usuarioPerfiles);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param usuarioPerfilDto
     * @return
     */
    public UsuarioPerfilDto saveUsuarioPerfil(UsuarioPerfilDto usuarioPerfilDto) {
        try {
            UsuarioPerfil usuarioPerfil = converter.getUsuarioPerfil(usuarioPerfilDto);

            usuarioPerfil.setAuditUsuario(usuarioLogueado.getUsername());
            usuarioPerfil = em.merge(usuarioPerfil);
            em.flush();

            return converter.getUsuarioPerfilDto(usuarioPerfil);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * 
     * @param id 
     */
    public void deleteUsuarioPerfil(Integer id) {
        try {
            UsuarioPerfil usuarioPerfil = (UsuarioPerfil) em.createNamedQuery("UsuarioPerfil.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(usuarioPerfil);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
