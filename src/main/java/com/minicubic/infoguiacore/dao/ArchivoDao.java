package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ArchivoCabDto;
import com.minicubic.infoguiacore.dto.ArchivoDetDto;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.ArchivoCab;
import com.minicubic.infoguiacore.model.ArchivoDet;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.converter.ArchivoConverter;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

    @PersistenceContext(unitName = "infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param archivoCabDto
     * @return
     */
    public ArchivoCabDto getArchivo(ArchivoCabDto archivoCabDto) {
        return this.getArchivo(archivoCabDto.getTablaRef(),
                archivoCabDto.getColumnaRef(),
                archivoCabDto.getIdRef());
    }

    /**
     * 
     * @param tablaRef
     * @param columnaRef
     * @param idRef
     * @return 
     */
    public ArchivoCabDto getArchivo(String tablaRef, String columnaRef, String idRef) {
        try {
            ArchivoCab archivoCab = (ArchivoCab) em.createNamedQuery("ArchivoCab.findByRef")
                    .setParameter("tablaRef", tablaRef)
                    .setParameter("columnaRef", columnaRef)
                    .setParameter("idRef", idRef)
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
     * @param id
     * @return 
     */
    public ArchivoDetDto getArchivoDetalle(Integer id) {
        try {
            ArchivoDet archivoDet = (ArchivoDet) em.createNamedQuery("ArchivoDet.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getArchivoDetDto(archivoDet);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Guardar archivos con multiple detalles. Por cada archivo nuevo crea
     * nuevos detalles.
     *
     * @param archivoCabDto
     * @return
     */
    public ArchivoCabDto saveArchivoMultipleDetalle(ArchivoCabDto archivoCabDto) {
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
            if (!Util.isEmpty(archivoCab)) {
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
     * Guarda un archivo creando un solo detalle. Por cada archivo nuevo Se
     * actuliza el registro de detalle.
     *
     * @param archivoCabDto
     * @return
     */
    public ArchivoCabDto saveArchivoUnDetalle(ArchivoCabDto archivoCabDto) {
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
            if (!Util.isEmpty(archivoCab)) {
                ArchivoDet archivoDetDB = archivoCab.getArchivosDet().iterator().next();
                ArchivoDet archivoDetAux = archivoCabAux.getArchivosDet().iterator().next();

                archivoDetDB.setMimeType(archivoDetAux.getMimeType());
                archivoDetDB.setNombre(archivoDetAux.getNombre());
                archivoDetDB.setUbicacion(archivoDetAux.getUbicacion());
                archivoDetDB.setUrl(archivoDetAux.getUrl());
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
    public void deleteArchivoDetalle(Integer id) {
        try {
            ArchivoDet archivoDet = (ArchivoDet) em.createNamedQuery("ArchivoDet.findById")
                    .setParameter("id", id)
                    .getSingleResult();
            
            em.remove(archivoDet);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
