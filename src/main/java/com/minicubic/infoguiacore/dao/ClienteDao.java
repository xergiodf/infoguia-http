package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.Cliente;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.converter.ClienteConverter;
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
public class ClienteDao {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final ClienteConverter converter = new ClienteConverter();
    private static final Logger LOG = Logger.getLogger("ClienteDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public ClienteDto getCliente(Long id) {
        try {
            Cliente cliente = (Cliente) em.createNamedQuery("Cliente.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getClienteDto(cliente);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param clienteDto
     * @return 
     */
    public ClienteDto getClienteByParam(ClienteDto clienteDto) {
        try {
            
            Cliente cliente = null;
                    
            if ( !Util.isEmpty(clienteDto.getCodigoCliente()) ) {
                cliente = (Cliente) em.createNamedQuery("Cliente.findByCodigoCliente")
                    .setParameter("codigoCliente", clienteDto.getCodigoCliente().toUpperCase())
                    .getSingleResult();
            }

            return converter.getClienteDto(cliente);
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
    public List<ClienteDto> getClientes() {
        try {
            List<Cliente> clientes = em.createNamedQuery("Cliente.findAll")
                    .getResultList();

            return converter.getClientesDto(clientes);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clienteDto
     * @return
     */
    public ClienteDto saveCliente(ClienteDto clienteDto) {
        try {
            Cliente cliente = converter.getCliente(clienteDto);

            cliente.setAuditUsuario(usuarioLogueado.getUsername());
            cliente = em.merge(cliente);
            em.flush();

            return converter.getClienteDto(cliente);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteCliente(Long id) {
        try {
            Cliente cliente = (Cliente) em.createNamedQuery("Cliente.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(cliente);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
