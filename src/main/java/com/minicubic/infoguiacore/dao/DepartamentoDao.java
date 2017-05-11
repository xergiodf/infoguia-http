package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.DepartamentoDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.Departamento;
import com.minicubic.infoguiacore.util.converter.DepartamentoConverter;
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
public class DepartamentoDao {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final DepartamentoConverter converter = new DepartamentoConverter();
    private static final Logger LOG = Logger.getLogger("DepartamentoDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public DepartamentoDto getDepartamento(Integer id) {
        try {
            Departamento departamento = (Departamento) em.createNamedQuery("Departamento.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getDepartamentoDto(departamento);
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
    public List<DepartamentoDto> getDepartamentos() {
        try {
            List<Departamento> departamentos = em.createNamedQuery("Departamento.findAll")
                    .getResultList();

            return converter.getDepartamentosDto(departamentos);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param departamentoDto
     * @return
     */
    public DepartamentoDto saveDepartamento(DepartamentoDto departamentoDto) {
        try {
            Departamento departamento = converter.getDepartamento(departamentoDto);

            departamento.setAuditUsuario(usuarioLogueado.getUsername());
            departamento = em.merge(departamento);
            em.flush();

            return converter.getDepartamentoDto(departamento);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteDepartamento(Integer id) {
        try {
            Departamento departamento = (Departamento) em.createNamedQuery("Departamento.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(departamento);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
