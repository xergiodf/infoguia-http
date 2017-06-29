package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.dto.TipoUsuarioDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.model.TipoUsuario;
import com.minicubic.infoguiahttp.util.converter.TipoUsuarioConverter;
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
 * @version 1 - 27.04.2017
 */
public class TipoUsuarioDao {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final TipoUsuarioConverter converter = new TipoUsuarioConverter();
    private static final Logger LOG = Logger.getLogger("TipoUsuarioDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public TipoUsuarioDto getTipoUsuario(Integer id) {
        try {
            TipoUsuario tipoUsuario = (TipoUsuario) em.createNamedQuery("TipoUsuario.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getTipoUsuarioDto(tipoUsuario);
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
    public List<TipoUsuarioDto> getTipoUsuarios() {
        try {
            List<TipoUsuario> tipoUsuarios = em.createNamedQuery("TipoUsuario.findAll")
                    .getResultList();

            return converter.getTipoUsuariosDto(tipoUsuarios);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param tipoUsuarioDto
     * @return
     */
    public TipoUsuarioDto saveTipoUsuario(TipoUsuarioDto tipoUsuarioDto) {
        try {
            TipoUsuario tipoUsuario = converter.getTipoUsuario(tipoUsuarioDto);

            tipoUsuario.setAuditUsuario(usuarioLogueado.getUsername());
            tipoUsuario = em.merge(tipoUsuario);
            em.flush();

            return converter.getTipoUsuarioDto(tipoUsuario);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteTipoUsuario(Integer id) {
        try {
            TipoUsuario tipoUsuario = (TipoUsuario) em.createNamedQuery("TipoUsuario.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(tipoUsuario);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
