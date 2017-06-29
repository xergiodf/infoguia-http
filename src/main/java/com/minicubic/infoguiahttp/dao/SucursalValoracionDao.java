package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.dto.SucursalValoracionCabDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.model.SucursalValoracionCab;
import com.minicubic.infoguiahttp.model.SucursalValoracionDet;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.converter.SucursalValoracionConverter;
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
 * @version 1 - 08.06.2017
 */
public class SucursalValoracionDao {

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    private final SucursalValoracionConverter converter = new SucursalValoracionConverter();
    private static final Logger LOG = Logger.getLogger("SucursalValoracionDao");

    @PersistenceContext(unitName = "infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public SucursalValoracionCabDto getSucursalValoracionCab(Integer id) {
        try {
            SucursalValoracionCab sucursalValoracionCab = (SucursalValoracionCab) em.createNamedQuery("SucursalValoracionCab.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getSucursalValoracionCabDto(sucursalValoracionCab);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param idCliente
     * @return
     */
    public List<SucursalValoracionCabDto> getSucursalValoracionesCabByCliente(Long idCliente) {
        try {
            List<SucursalValoracionCab> sucursalValoracionesCab = em.createNamedQuery("SucursalValoracionCab.findByCliente")
                    .setParameter("idCliente", idCliente)
                    .getResultList();

            return converter.getSucursalValoracionesCabDto(sucursalValoracionesCab);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param idClienteSucursal
     * @return
     */
    public List<SucursalValoracionCabDto> getSucursalValoracionesCabByClienteSucursal(Integer idClienteSucursal) {
        try {
            List<SucursalValoracionCab> sucursalValoracionesCab = em.createNamedQuery("SucursalValoracionCab.findByClienteSucursal")
                    .setParameter("idClienteSucursal", idClienteSucursal)
                    .getResultList();

            return converter.getSucursalValoracionesCabDto(sucursalValoracionesCab);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param sucursalValoracionCabDto
     * @return
     */
    public SucursalValoracionCabDto saveSucursalValoracionCab(SucursalValoracionCabDto sucursalValoracionCabDto) {
        try {
            SucursalValoracionCab sucursalValoracionCabAux = converter.getSucursalValoracionCab(sucursalValoracionCabDto);
            SucursalValoracionCab sucursalValoracionCabBD;

            // Verificamos si no existe una cabecera para esta sucursal
            try {
                sucursalValoracionCabBD = (SucursalValoracionCab) em.createNamedQuery("SucursalValoracionCab.findByClienteSucursal")
                        .setParameter("idClienteSucursal", sucursalValoracionCabDto.getClienteSucursalDto().getId())
                        .getSingleResult();
            } catch (NoResultException nre) {
                sucursalValoracionCabBD = null;
            }

            // Guardamos la cabecera
            if (!Util.isEmpty(sucursalValoracionCabBD)) {
                // Verificar si no existe un comentario para el usuario, en ese caso se trata de una actualizacion
                SucursalValoracionDet sucursalValoracionDetDB;
                try {
                    sucursalValoracionDetDB = (SucursalValoracionDet) em.createNamedQuery("SucursalValoracionDet.findByUsuario")
                            .setParameter("idUsuario", sucursalValoracionCabDto.getSucursalValoracionesDetDto().iterator().next().getUsuarioDto().getId())
                            .getSingleResult();
                } catch (NoResultException nre) {
                    sucursalValoracionDetDB = null;
                }

                if (!Util.isEmpty(sucursalValoracionDetDB)) {
                    // Es un update
                    SucursalValoracionDet sucursalValoracionDetAux = sucursalValoracionCabAux.getSucursalValoracionesDet().iterator().next();
                    sucursalValoracionDetDB.setMensaje(sucursalValoracionDetAux.getMensaje());
                    sucursalValoracionDetDB.setPuntaje(sucursalValoracionDetAux.getPuntaje());

                    sucursalValoracionCabBD.getSucursalValoracionesDet().add(sucursalValoracionDetDB);

                } else {
                    sucursalValoracionCabAux.getSucursalValoracionesDet().iterator().next().setSucursalValoracionCab(sucursalValoracionCabBD);
                    sucursalValoracionCabBD.setSucursalValoracionesDet(sucursalValoracionCabAux.getSucursalValoracionesDet());
                }
            } else {
                sucursalValoracionCabBD = sucursalValoracionCabAux;
            }

            sucursalValoracionCabBD.setPuntajeTotal(null);
            sucursalValoracionCabBD.setAuditUsuario(usuarioLogueado.getUsername());
            sucursalValoracionCabBD = em.merge(sucursalValoracionCabBD);

            em.flush();

            return converter.getSucursalValoracionCabDto(sucursalValoracionCabBD);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param id
     */
    public void deleteSucursalValoracion(Integer id) {
        try {
            SucursalValoracionCab sucursalValoracion = (SucursalValoracionCab) em.createNamedQuery("SucursalValoracionCab.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(sucursalValoracion);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
