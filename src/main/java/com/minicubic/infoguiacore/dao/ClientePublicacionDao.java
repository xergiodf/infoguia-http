package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClientePublicacionDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.enums.TableReference;
import com.minicubic.infoguiacore.model.ClientePublicacion;
import com.minicubic.infoguiacore.util.converter.ArchivoConverter;
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
    
    @Inject
    private ArchivoDao archivoDao;
    
    private final ClientePublicacionConverter converter = new ClientePublicacionConverter();
    private final ArchivoConverter archivoConverter = new ArchivoConverter();
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
            ClientePublicacionDto clientePublicacionDto = converter.getClientePublicacionDto(
                    (ClientePublicacion) em.createNamedQuery("ClientePublicacion.findById")
                        .setParameter("id", id)
                        .getSingleResult()
            );
            
            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            clientePublicacionDto.setArchivos(
                    archivoConverter.getArchivoDto(
                            archivoDao.getArchivo(
                                    TableReference.CLIENTE_PUBLICACION.getTableName(), 
                                    TableReference.CLIENTE_PUBLICACION.getIdColumnName(), 
                                    id.toString()
                            )
                    )
            );

            return clientePublicacionDto;
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
            List<ClientePublicacionDto> clientePublicacionesDto = converter.getClientePublicacionesDto(
                    em.createNamedQuery("ClientePublicacion.findAll").getResultList()
            );
            
            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            
            for ( ClientePublicacionDto clientePublicacionDto : clientePublicacionesDto ) {
                clientePublicacionDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.CLIENTE_PUBLICACION.getTableName(), 
                                        TableReference.CLIENTE_PUBLICACION.getIdColumnName(), 
                                        clientePublicacionDto.getId().toString()
                                )
                        )
                );
            }
            
            return clientePublicacionesDto;
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
