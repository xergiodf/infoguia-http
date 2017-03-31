package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClientePublicacionDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.ClientePublicacion;
import com.minicubic.infoguiacore.util.converter.ClientePublicacionConverter;
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
public class ClientePublicacionDao {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    private final ClientePublicacionConverter converter = new ClientePublicacionConverter();
    private static final Logger LOG = Logger.getLogger("ClientePublicacionDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public ClientePublicacionDto getClientePublicacion(Integer id) {
        try {
            ClientePublicacion clientePublicacion = (ClientePublicacion) em.createNamedQuery("ClientePublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getClientePublicacionDto(clientePublicacion);
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
    public List<ClientePublicacionDto> getClientePublicaciones() {
        try {
            List<ClientePublicacion> clientePublicaciones = em.createNamedQuery("ClientePublicacion.findAll")
                    .getResultList();

            return converter.getClientePublicacionesDto(clientePublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param tipoPublicacionId
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicacionesByTipoPublicacion(Integer tipoPublicacionId) {
        try {
            List<ClientePublicacion> clientePublicaciones = em.createNamedQuery("ClientePublicacion.findByTipoPublicacion")
                    .setParameter("tipoPublicacionId", tipoPublicacionId)
                    .getResultList();

            return converter.getClientePublicacionesDto(clientePublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param clienteId
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicacionesByCliente(Long clienteId) {
        try {
            List<ClientePublicacion> clientePublicaciones = em.createNamedQuery("ClientePublicacion.findByCliente")
                    .setParameter("clienteId", clienteId)
                    .getResultList();

            return converter.getClientePublicacionesDto(clientePublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clientePublicacionDto
     * @return
     */
    public ClientePublicacionDto saveClientePublicacion(ClientePublicacionDto clientePublicacionDto) {
        try {
            ClientePublicacion clientePublicacion = converter.getClientePublicacion(clientePublicacionDto);

            clientePublicacion.setAuditUsuario(usuarioLogueado.getUsername());
            clientePublicacion = em.merge(clientePublicacion);
            em.flush();

            return converter.getClientePublicacionDto(clientePublicacion);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteClientePublicacion(Integer id) {
        try {
            ClientePublicacion clientePublicacion = (ClientePublicacion) em.createNamedQuery("ClientePublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(clientePublicacion);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

}
