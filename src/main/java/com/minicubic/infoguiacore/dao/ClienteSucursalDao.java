package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClienteSucursalDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.enums.TableReference;
import com.minicubic.infoguiacore.model.ClienteSucursal;
import com.minicubic.infoguiacore.util.converter.ArchivoConverter;
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
    
    @Inject
    private ArchivoDao archivoDao;

    private final ClienteSucursalConverter converter = new ClienteSucursalConverter();
    private final ArchivoConverter archivoConverter = new ArchivoConverter();
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
            ClienteSucursalDto clienteSucursalDto = converter.getClienteSucursalDto(
                    (ClienteSucursal) em.createNamedQuery("ClienteSucursal.findById")
                        .setParameter("id", id)
                        .getSingleResult()
            );
            
            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            clienteSucursalDto.setArchivos(
                    archivoConverter.getArchivoDto(
                            archivoDao.getArchivo(
                                    TableReference.CLIENTE_SUCURSAL.getTableName(),
                                    TableReference.CLIENTE_SUCURSAL.getIdColumnName(),
                                    id.toString()
                            )
                    )
            );

            return clienteSucursalDto;
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
            List<ClienteSucursalDto> clienteSucursalesDto = converter.getClienteSucursalesDto(
                    em.createNamedQuery("ClienteSucursal.findAll").getResultList()
            );
            
            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            for ( ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto ) {
                clienteSucursalDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.CLIENTE_SUCURSAL.getTableName(),
                                        TableReference.CLIENTE_SUCURSAL.getIdColumnName(),
                                        clienteSucursalDto.getId().toString()
                                )
                        )
                );
            }

            return clienteSucursalesDto;
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

            List<ClienteSucursalDto> clienteSucursalesDto = converter.getClienteSucursalesDto(
                    em.createNamedQuery("ClienteSucursal.findByCliente")
                    .setParameter("clienteId", clienteSucursalId)
                    .getResultList()
            );
            
            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            for ( ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto ) {
                clienteSucursalDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.CLIENTE_SUCURSAL.getTableName(),
                                        TableReference.CLIENTE_SUCURSAL.getIdColumnName(),
                                        clienteSucursalDto.getId().toString()
                                )
                        )
                );
            }

            return clienteSucursalesDto;
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param params
     * @return 
     */
    public List<ClienteSucursalDto> getClienteSucursalesByParams(String params) {
        try {
            List<ClienteSucursalDto> clienteSucursalesDto = converter.getClienteSucursalesDto(em.createNamedQuery("ClienteSucursal.findByParams")
                    .setParameter("params", ("%" + params.replace(" ", "%") + "%"))
                    .getResultList()
            );

            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            for ( ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto ) {
                clienteSucursalDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.CLIENTE_SUCURSAL.getTableName(),
                                        TableReference.CLIENTE_SUCURSAL.getIdColumnName(),
                                        clienteSucursalDto.getId().toString()
                                )
                        )
                );
            }

            return clienteSucursalesDto;
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
