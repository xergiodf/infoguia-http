package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.CiudadDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.Ciudad;
import com.minicubic.infoguiacore.util.converter.CiudadConverter;
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
 * @version 1 - 11.05.2017
 */
public class CiudadDao {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final CiudadConverter converter = new CiudadConverter();
    private static final Logger LOG = Logger.getLogger("CiudadDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public CiudadDto getCiudad(Integer id) {
        try {
            Ciudad ciudad = (Ciudad) em.createNamedQuery("Ciudad.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getCiudadDto(ciudad);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param idDepartamento
     * @return 
     */
    public List<CiudadDto> getCiudadesByDepartamento(Integer idDepartamento) {
        try {
            List<Ciudad> ciudades = em.createNamedQuery("Ciudad.findByDepartamento")
                    .setParameter("idDepartamento", idDepartamento)
                    .getResultList();

            return converter.getCiudadesDto(ciudades);
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
    public List<CiudadDto> getCiudades() {
        try {
            List<Ciudad> ciudades = em.createNamedQuery("Ciudad.findAll")
                    .getResultList();

            return converter.getCiudadesDto(ciudades);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param ciudadDto
     * @return
     */
    public CiudadDto saveCiudad(CiudadDto ciudadDto) {
        try {
            Ciudad ciudad = converter.getCiudad(ciudadDto);

            ciudad.setAuditUsuario(usuarioLogueado.getUsername());
            ciudad = em.merge(ciudad);
            em.flush();

            return converter.getCiudadDto(ciudad);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteCiudad(Integer id) {
        try {
            Ciudad ciudad = (Ciudad) em.createNamedQuery("Ciudad.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(ciudad);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
