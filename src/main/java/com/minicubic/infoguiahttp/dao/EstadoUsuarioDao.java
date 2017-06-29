package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.dto.EstadoUsuarioDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.model.EstadoUsuario;
import com.minicubic.infoguiahttp.util.converter.EstadoUsuarioConverter;
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
public class EstadoUsuarioDao {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final EstadoUsuarioConverter converter = new EstadoUsuarioConverter();
    private static final Logger LOG = Logger.getLogger("EstadoUsuarioDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public EstadoUsuarioDto getEstadoUsuario(Integer id) {
        try {
            EstadoUsuario estadoUsuario = (EstadoUsuario) em.createNamedQuery("EstadoUsuario.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getEstadoUsuarioDto(estadoUsuario);
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
    public List<EstadoUsuarioDto> getEstadoUsuarios() {
        try {
            List<EstadoUsuario> estadoUsuarios = em.createNamedQuery("EstadoUsuario.findAll")
                    .getResultList();

            return converter.getEstadoUsuariosDto(estadoUsuarios);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param estadoUsuarioDto
     * @return
     */
    public EstadoUsuarioDto saveEstadoUsuario(EstadoUsuarioDto estadoUsuarioDto) {
        try {
            EstadoUsuario estadoUsuario = converter.getEstadoUsuario(estadoUsuarioDto);

            estadoUsuario.setAuditUsuario(usuarioLogueado.getUsername());
            estadoUsuario = em.merge(estadoUsuario);
            em.flush();

            return converter.getEstadoUsuarioDto(estadoUsuario);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteEstadoUsuario(Integer id) {
        try {
            EstadoUsuario estadoUsuario = (EstadoUsuario) em.createNamedQuery("EstadoUsuario.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(estadoUsuario);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
