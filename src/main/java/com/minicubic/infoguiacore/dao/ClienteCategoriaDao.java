package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClienteCategoriaDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.ClienteCategoria;
import com.minicubic.infoguiacore.model.ClienteCategoriaPK;
import com.minicubic.infoguiacore.util.converter.ClienteCategoriaConverter;
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
public class ClienteCategoriaDao {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    private final ClienteCategoriaConverter converter = new ClienteCategoriaConverter();
    private static final Logger LOG = Logger.getLogger("ClienteCategoriaDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param idCliente
     * @return
     */
    public List<ClienteCategoriaDto> getClienteCategoriasByCliente(Long idCliente) {
        try {
            List<ClienteCategoria> clienteCategorias = em.createNamedQuery("ClienteCategoria.findByIdCliente")
                    .setParameter("idCliente", idCliente)
                    .getResultList();

            return converter.getClienteCategoriasDto(clienteCategorias);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param idCategoria
     * @return 
     */
    public List<ClienteCategoriaDto> getClienteCategoriasByCategoria(Integer idCategoria) {
        try {
            List<ClienteCategoria> clienteCategorias = em.createNamedQuery("ClienteCategoria.findByIdCategoria")
                    .setParameter("idCategoria", idCategoria)
                    .getResultList();

            return converter.getClienteCategoriasDto(clienteCategorias);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clienteCategoriaDto
     * @return
     */
    public ClienteCategoriaDto saveClienteCategoria(ClienteCategoriaDto clienteCategoriaDto) {
        try {
            ClienteCategoria clienteCategoria = converter.getClienteCategoria(clienteCategoriaDto);

            clienteCategoria.setAuditUsuario(usuarioLogueado.getUsername());
            clienteCategoria = em.merge(clienteCategoria);
            em.flush();

            return converter.getClienteCategoriaDto(clienteCategoria);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * 
     * @param id 
     */
    public void deleteClienteCategoria(ClienteCategoriaPK id) {
        try {
            ClienteCategoria categoria = (ClienteCategoria) em.createNamedQuery("ClienteCategoria.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(categoria);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
