package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.EstadoPublicacionDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.EstadoPublicacion;
import com.minicubic.infoguiacore.util.converter.EstadoPublicacionConverter;
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
public class EstadoPublicacionDao {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final EstadoPublicacionConverter converter = new EstadoPublicacionConverter();
    private static final Logger LOG = Logger.getLogger("EstadoPublicacionDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public EstadoPublicacionDto getEstadoPublicacion(Integer id) {
        try {
            EstadoPublicacion estadoPublicacion = (EstadoPublicacion) em.createNamedQuery("EstadoPublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getEstadoPublicacionDto(estadoPublicacion);
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
    public List<EstadoPublicacionDto> getEstadoPublicaciones() {
        try {
            List<EstadoPublicacion> estadoPublicaciones = em.createNamedQuery("EstadoPublicacion.findAll")
                    .getResultList();

            return converter.getEstadoPublicacionesDto(estadoPublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param estadoPublicacionDto
     * @return
     */
    public EstadoPublicacionDto saveEstadoPublicacion(EstadoPublicacionDto estadoPublicacionDto) {
        try {
            EstadoPublicacion estadoPublicacion = converter.getEstadoPublicacion(estadoPublicacionDto);

            estadoPublicacion.setAuditUsuario(usuarioLogueado.getUsername());
            estadoPublicacion = em.merge(estadoPublicacion);
            em.flush();

            return converter.getEstadoPublicacionDto(estadoPublicacion);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteEstadoPublicacion(Integer id) {
        try {
            EstadoPublicacion estadoPublicacion = (EstadoPublicacion) em.createNamedQuery("EstadoPublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(estadoPublicacion);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
