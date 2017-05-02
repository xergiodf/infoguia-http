package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.TipoPublicacionDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.TipoPublicacion;
import com.minicubic.infoguiacore.util.converter.TipoPublicacionConverter;
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
public class TipoPublicacionDao {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final TipoPublicacionConverter converter = new TipoPublicacionConverter();
    private static final Logger LOG = Logger.getLogger("TipoPublicacionDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public TipoPublicacionDto getTipoPublicacion(Integer id) {
        try {
            TipoPublicacion tipoPublicacion = (TipoPublicacion) em.createNamedQuery("TipoPublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getTipoPublicacionDto(tipoPublicacion);
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
    public List<TipoPublicacionDto> getTipoPublicaciones() {
        try {
            List<TipoPublicacion> tipoPublicaciones = em.createNamedQuery("TipoPublicacion.findAll")
                    .getResultList();

            return converter.getTipoPublicacionesDto(tipoPublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param tipoPublicacionDto
     * @return
     */
    public TipoPublicacionDto saveTipoPublicacion(TipoPublicacionDto tipoPublicacionDto) {
        try {
            TipoPublicacion tipoPublicacion = converter.getTipoPublicacion(tipoPublicacionDto);

            tipoPublicacion.setAuditUsuario(usuarioLogueado.getUsername());
            tipoPublicacion = em.merge(tipoPublicacion);
            em.flush();

            return converter.getTipoPublicacionDto(tipoPublicacion);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteTipoPublicacion(Integer id) {
        try {
            TipoPublicacion tipoPublicacion = (TipoPublicacion) em.createNamedQuery("TipoPublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(tipoPublicacion);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
