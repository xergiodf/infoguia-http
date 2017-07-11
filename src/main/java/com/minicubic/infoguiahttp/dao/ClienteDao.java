package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.dto.ClienteDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.model.Cliente;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.converter.ClienteConverter;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.enums.TableReference;
import com.minicubic.infoguiahttp.util.converter.ArchivoConverter;
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

    @Inject
    private ArchivoDao archivoDao;

    private final ClienteConverter converter = new ClienteConverter();
    private final ArchivoConverter archivoConverter = new ArchivoConverter();
    private static final Logger LOG = Logger.getLogger("ClienteDao");

    @PersistenceContext(unitName = "infoGuiaPU")
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

            ClienteDto clienteDto = converter.getClienteDto(cliente);

            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            clienteDto.setArchivos(
                    archivoConverter.getArchivoDto(
                            archivoDao.getArchivo(
                                    TableReference.CLIENTE.getTableName(),
                                    TableReference.CLIENTE.getIdColumnName(),
                                    id.toString()
                            )
                    )
            );

            return clienteDto;
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clienteParam
     * @return
     */
    public ClienteDto getClienteByParam(ClienteDto clienteParam) {
        try {

            Cliente cliente = null;
            ClienteDto clienteDto = null;

            if (!Util.isEmpty(clienteParam.getCodigoCliente())) {
                cliente = (Cliente) em.createNamedQuery("Cliente.findByCodigoCliente")
                        .setParameter("codigoCliente", clienteParam.getCodigoCliente().toUpperCase())
                        .getSingleResult();

                clienteDto = converter.getClienteDto(cliente);

                // TODO: Buscar algun patron de disenho que mejore esto
                // Cargamos las imagenes (si tiene)
                clienteDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.CLIENTE.getTableName(),
                                        TableReference.CLIENTE.getIdColumnName(),
                                        clienteDto.getId().toString()
                                )
                        )
                );
            }

            return clienteDto;
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

            List<ClienteDto> clientesDto = converter.getClientesDto(clientes);

            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            for (ClienteDto clienteDto : clientesDto) {
                clienteDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.CLIENTE.getTableName(),
                                        TableReference.CLIENTE.getIdColumnName(),
                                        clienteDto.getId().toString()
                                )
                        )
                );
            }

            return clientesDto;
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
    public List<ClienteDto> getClientesByParams(String params) {
        try {
            List<Cliente> clientes = em.createNamedQuery("Cliente.findByParams")
                    .setParameter("params", ("%" + params.replace(" ", "%") + "%"))
                    .getResultList();

            List<ClienteDto> clientesDto = converter.getClientesDto(clientes);

            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            for (ClienteDto clienteDto : clientesDto) {
                clienteDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.CLIENTE.getTableName(),
                                        TableReference.CLIENTE.getIdColumnName(),
                                        clienteDto.getId().toString()
                                )
                        )
                );
            }

            return clientesDto;
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
