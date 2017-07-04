package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.dto.CategoriaDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.model.Categoria;
import com.minicubic.infoguiahttp.util.converter.CategoriaConverter;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.dto.GrupoCategoriaDto;
import com.minicubic.infoguiahttp.model.GrupoCategoria;
import com.minicubic.infoguiahttp.util.converter.GrupoCategoriaConverter;
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
public class CategoriaDao {
    
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final CategoriaConverter converter = new CategoriaConverter();
    private static final Logger LOG = Logger.getLogger("CategoriaDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public CategoriaDto getCategoria(Integer id) {
        try {
            Categoria categoria = (Categoria) em.createNamedQuery("Categoria.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getCategoriaDto(categoria);
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
    public List<CategoriaDto> getCategorias() {
        try {
            List<Categoria> categorias = em.createNamedQuery("Categoria.findAll")
                    .getResultList();

            return converter.getCategoriasDto(categorias);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param categoriaDto
     * @return
     */
    public CategoriaDto saveCategoria(CategoriaDto categoriaDto) {
        try {
            Categoria categoria = converter.getCategoria(categoriaDto);

            categoria.setAuditUsuario(usuarioLogueado.getUsername());
            categoria = em.merge(categoria);
            em.flush();

            return converter.getCategoriaDto(categoria);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteCategoria(Integer id) {
        try {
            Categoria categoria = (Categoria) em.createNamedQuery("Categoria.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(categoria);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public List<GrupoCategoriaDto> getGruposCategorias() {
        try {
            List<GrupoCategoria> grupoCategorias = em.createNamedQuery("GrupoCategoria.findAll")
                    .getResultList();

            return (new GrupoCategoriaConverter()).getGrupoCategoriasDto(grupoCategorias);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
