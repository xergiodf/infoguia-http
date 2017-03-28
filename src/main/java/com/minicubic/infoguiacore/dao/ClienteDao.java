package com.minicubic.infoguiacore.dao;

import java.io.Serializable;
import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.NovedadesDto;
import com.minicubic.infoguiacore.model.Cliente;
import com.minicubic.infoguiacore.util.converter.ClienteConverter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xergio
 */
public class ClienteDao implements Serializable{

    private final ClienteConverter converter = new ClienteConverter();
    private static final Logger LOG = Logger.getLogger("ClienteService");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    public List<Cliente> getClientes(Cliente params) {
        try {
            LOG.info("getClientes... ");
            Query query = em.createNativeQuery("select * from gndb.clientes c where c.nombre_corto  like '" + params.getNombreCorto() + "%'", Cliente.class);
            LOG.log(Level.INFO, "Se encontraron {0} registros", query.getResultList().size());
            return query.getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error ", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
 

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
    public List<ClienteDto> getClientes(ClienteDto clienteDto) {
        try {
            LOG.info("getClientesPorSucursal... ");
            Query query = em.createNativeQuery("SELECT com.minicubic.infoguiacore.dto.ClientesDTO(c.id, c.nombreCompleto, "
                    + "c.nombreCorto, cs.nombreSucursal, cs.direccionFisica, cs.coordenadas, cs.horarios, cs.telefono "
                    + ") FROM ClienteSucursales cs join Cliente c where c.nombre_corto like '" + clienteDto.getNombreCorto() + "%'", ClienteDto.class);
            LOG.log(Level.INFO, "Se encontraron {0} registros", query.getResultList().size());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<NovedadesDto> getNovedades() {
        try {
            LOG.info("getNovedades...");
            return em.createQuery("SELECT new com.minicubic.infoguiacore.dto.NovedadesDTO(cp.id, cp.titulo, cp.descripcionCorta, cp.dirImagen, c.id as idcliente, c.nombreCompleto, "
                                                + "tp.descripcion as tipo_publicacion) FROM ClientePublicaciones \n" +
                                                "cp join cp.idCliente c join cp.tipoPublicacionesId tp ").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
