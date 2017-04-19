package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ArchivoCabDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.ArchivoCab;
import com.minicubic.infoguiacore.model.ArchivoDet;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.converter.ArchivoConverter;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
 * @version 1 - 18/04/2017
 */
public class ArchivoDao {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final ArchivoConverter converter = new ArchivoConverter();
    private static final Logger LOG = Logger.getLogger("ArchivoDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public ArchivoCabDto getArchivo(Integer id) {
        try {
            ArchivoCab archivoCab = (ArchivoCab) em.createNamedQuery("ArchivoCab.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getArchivoCabDto(archivoCab);
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
    public List<ArchivoCabDto> getArchivos() {
        try {
            List<ArchivoCab> archivoCabs = em.createNamedQuery("ArchivoCab.findAll")
                    .getResultList();

            return converter.getArchivosCabDto(archivoCabs);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param archivoCabDto
     * @return
     */
    public ArchivoCabDto saveArchivo(ArchivoCabDto archivoCabDto) {
        try {
            ArchivoCab archivoCabAux = converter.getArchivoCab(archivoCabDto);
            ArchivoCab archivoCab;
            
            // Verificamos si no existe una cabecera para este tipo de archivo
            try {
                archivoCab = (ArchivoCab) em.createNamedQuery("ArchivoCab.findByRef")
                    .setParameter("tablaRef", archivoCabDto.getTablaRef())
                    .setParameter("columnaRef", archivoCabDto.getColumnaRef())
                    .setParameter("idRef", archivoCabDto.getIdRef())
                    .getSingleResult();
            } catch (NoResultException nre) {
                archivoCab = null;
            }
            
            // Guardamos la cabecera
            if ( !Util.isEmpty(archivoCab) ) {
                // TODO: Mejorar despues
                archivoCabAux.getArchivosDet().iterator().next().setArchivoCab(archivoCab);
                archivoCab.setArchivosDet(new ArrayList<ArchivoDet>());
                archivoCab.getArchivosDet().addAll(archivoCabAux.getArchivosDet());
            } else {
                archivoCab = archivoCabAux;
            }
            
            archivoCab.setAuditUsuario(usuarioLogueado.getUsername());
            archivoCab = em.merge(archivoCab);
            
            em.flush();
            
            return converter.getArchivoCabDto(archivoCab);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * 
     * @param id 
     */
    public void deleteArchivo(Integer id) {
        try {
            ArchivoCab archivoCab = (ArchivoCab) em.createNamedQuery("ArchivoCab.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            // Borramos los detalles
            ArchivoDet archivoDetAux;
            for ( ArchivoDet archivoDet : archivoCab.getArchivosDet() ) {
                archivoDetAux = (ArchivoDet) em.createNamedQuery("ArchivoCab.findById")
                    .setParameter("id", archivoDet.getId())
                    .getSingleResult();
                
                em.remove(archivoDet);
            }
            
            //Borramos la cabecera
            em.remove(archivoCab);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
