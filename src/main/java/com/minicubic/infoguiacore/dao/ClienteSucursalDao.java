package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClienteSucursalDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.ClienteSucursal;
import com.minicubic.infoguiacore.util.converter.ClienteSucursalConverter;
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
public class ClienteSucursalDao {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final ClienteSucursalConverter converter = new ClienteSucursalConverter();
    private static final Logger LOG = Logger.getLogger("ClienteSucursalDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public ClienteSucursalDto getClienteSucursal(Integer id) {
        try {
            ClienteSucursal clienteSucursal = (ClienteSucursal) em.createNamedQuery("ClienteSucursal.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getClienteSucursalDto(clienteSucursal);
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
    public List<ClienteSucursalDto> getClienteSucursales() {
        try {
            List<ClienteSucursal> clienteSucursals = em.createNamedQuery("ClienteSucursal.findAll")
                    .getResultList();

            return converter.getClienteSucursalesDto(clienteSucursals);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param clienteSucursalId
     * @return 
     */
    public List<ClienteSucursalDto> getClienteSucursalesByCliente(Long clienteSucursalId) {
        try {
            List<ClienteSucursal> clienteSucursals = em.createNamedQuery("ClienteSucursal.findByCliente")
                    .setParameter("clienteId", clienteSucursalId)
                    .getResultList();

            return converter.getClienteSucursalesDto(clienteSucursals);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clienteSucursalSucursalDto
     * @return
     */
    public ClienteSucursalDto saveClienteSucursal(ClienteSucursalDto clienteSucursalSucursalDto) {
        try {
            ClienteSucursal clienteSucursal = converter.getClienteSucursal(clienteSucursalSucursalDto);

            clienteSucursal.setAuditUsuario(usuarioLogueado.getUsername());
            clienteSucursal = em.merge(clienteSucursal);
            em.flush();

            return converter.getClienteSucursalDto(clienteSucursal);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteClienteSucursal(Integer id) {
        try {
            ClienteSucursal clienteSucursal = (ClienteSucursal) em.createNamedQuery("ClienteSucursal.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(clienteSucursal);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
