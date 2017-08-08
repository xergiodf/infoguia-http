package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.dto.ClienteSucursalDto;
import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.enums.TableReference;
import com.minicubic.infoguiahttp.model.ClienteSucursal;
import com.minicubic.infoguiahttp.util.converter.ArchivoConverter;
import com.minicubic.infoguiahttp.util.converter.ClienteSucursalConverter;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.dto.ArchivoDto;
import com.minicubic.infoguiahttp.dto.SearchRequestDto;
import com.minicubic.infoguiahttp.dto.SucursalValoracionCabDto;
import com.minicubic.infoguiahttp.dto.TipoHorarioDto;
import com.minicubic.infoguiahttp.enums.TipoAccion;
import com.minicubic.infoguiahttp.model.SucursalHorarioCab;
import com.minicubic.infoguiahttp.model.SucursalHorarioDet;
import com.minicubic.infoguiahttp.model.UsuarioAccion;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Util;
import com.minicubic.infoguiahttp.util.converter.SucursalValoracionConverter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    @Inject
    private SucursalHorarioCabDao horarioCabDao;

    @Inject
    private SucursalValoracionDao valoracionDao;

    @Inject
    private UsuarioAccionDao favoritoDao;

    private final ClienteSucursalConverter converter = new ClienteSucursalConverter();
    private final ArchivoConverter archivoConverter = new ArchivoConverter();
    private static final Logger LOG = Logger.getLogger("ClienteSucursalDao");

    @PersistenceContext(unitName = "infoGuiaPU")
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
            setExtra(clienteSucursalDto);

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
            for (ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto) {
                setExtra(clienteSucursalDto);
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
            for (ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto) {
                setExtra(clienteSucursalDto);
            }

            return clienteSucursalesDto;
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo utilizado para las busquedas de la web
     *
     * @param params
     * @return
     */
    public List<ClienteSucursalDto> getClienteSucursalesByParams(SearchRequestDto params) {
        List<ClienteSucursalDto> clienteSucursalesDto = new ArrayList<>();

        try {
            if (Util.isEmpty(params.getQuery()) && !Util.isEmpty(params.getCategoryId())) {

                clienteSucursalesDto = converter.getClienteSucursalesDto(
                        em.createNamedQuery("ClienteSucursal.findByCategoria")
                                .setParameter("idCategoria", params.getCategoryId())
                                .setMaxResults(Constants.SEARCH_ROWS_PER_PAGE)
                                .setFirstResult(
                                        Util.isEmpty(params.getPage())
                                        ? 0
                                        : Constants.SEARCH_ROWS_PER_PAGE * params.getPage()
                                )
                                .getResultList()
                );

            } else {
                clienteSucursalesDto = converter.getClienteSucursalesDto(
                        em.createNamedQuery("ClienteSucursal.findByParams")
                                .setParameter("params", ("%" + params.getQuery().replace(" ", "%") + "%"))
                                .setMaxResults(Constants.SEARCH_ROWS_PER_PAGE)
                                .setFirstResult(
                                        Util.isEmpty(params.getPage())
                                        ? 0
                                        : Constants.SEARCH_ROWS_PER_PAGE * params.getPage()
                                )
                                .getResultList()
                );
            }

            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            for (ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto) {
                setExtra(clienteSucursalDto);
            }

            return clienteSucursalesDto;
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Long getCantidadClienteSucursalesByParams(SearchRequestDto params) {
        //List<ClienteSucursalDto> clienteSucursalesDto = new ArrayList<>();
        TypedQuery<Long> query = null;

        try {
            if (Util.isEmpty(params.getQuery()) && !Util.isEmpty(params.getCategoryId())) {

//                clienteSucursalesDto = converter.getClienteSucursalesDto(
//                        em.createNamedQuery("ClienteSucursal.findByCategoria")
//                                .setParameter("idCategoria", params.getCategoryId())
//                                .getResultList()
//                );
                query = em.createNamedQuery("ClienteSucursal.findByCategoria.count", Long.class);
                query.setParameter("idCategoria", params.getCategoryId());

            } else {
//                clienteSucursalesDto = converter.getClienteSucursalesDto(
//                        em.createNamedQuery("ClienteSucursal.findByParams")
//                                .setParameter("params", ("%" + params.getQuery().replace(" ", "%") + "%"))
//                                .getResultList()
//                );
                query = em.createNamedQuery("ClienteSucursal.findByParams.count", Long.class);
                query.setParameter("params", ("%" + params.getQuery().replace(" ", "%") + "%"));
            }

            //return clienteSucursalesDto.size();
            return query.getSingleResult();
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

    /**
     *
     * @param id
     */
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

    /**
     * En este metodo se invocan todos los metodos para cargar info extra dentro
     * de la respuesta
     *
     * @param obj
     */
    private void setExtra(ClienteSucursalDto obj) {
        // Imagenes
        obj.setArchivos(getArchivos(obj));

        // Horarios
        obj.setHorarioAtencion(getHorario(obj));

        // Valoracion
        obj.setValoracion(getValoracion(obj));

        // Favorito
        obj.setUsuarioFavorito(getUsuarioFavorito(obj));
    }

    /**
     * Obtiene los archivos asociados al registro
     *
     * @param obj
     * @return
     */
    private List<ArchivoDto> getArchivos(ClienteSucursalDto obj) {
        return archivoConverter.getArchivoDto(
                archivoDao.getArchivo(
                        TableReference.CLIENTE_SUCURSAL.getTableName(),
                        TableReference.CLIENTE_SUCURSAL.getIdColumnName(),
                        obj.getId().toString()
                )
        );
    }

    /**
     * Obtiene los horarios asociados al registro
     *
     * @param obj
     * @return
     */
    private String getHorario(ClienteSucursalDto obj) {
        String horario = "";

        SucursalHorarioCab horarioCab = horarioCabDao.findBySucursal(obj.getId());

        if (!Util.isEmpty(horarioCab)) {
            if (TipoHorarioDto.TIPOS.DIAS_ESPECIFICOS.getId().equals(
                    horarioCab.getTipoHorario().getId())) {

                List<SucursalHorarioDet> horarioDets = horarioCab.getSucursalHorariosDets();

                for (SucursalHorarioDet horarioDet : horarioDets) {
                    horario += horarioDet.getDias() + ", ";
                    horario += "Desde: " + horarioDet.getHoraDesde() + " ";
                    horario += "Hasta: " + horarioDet.getHoraHasta();
                }
            } else {
                horario = horarioCab.getTipoHorario().getDescripcion();
            }
        } else {
            horario = "HORARIO NO DISPONIBLE";
        }

        return horario;
    }

    /**
     * Obtiene las valoraciones asociadas al registro
     *
     * @param obj
     * @return
     */
    private SucursalValoracionCabDto getValoracion(ClienteSucursalDto obj) {
        try {
            return valoracionDao.getSucursalValoracionesCabByClienteSucursal(obj.getId()).iterator().next();
        } catch (NoResultException | NoSuchElementException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Indica si el registro se encuentra marcado como favorito por el usuario
     * logueado
     *
     * @param obj
     * @return
     */
    private Boolean getUsuarioFavorito(ClienteSucursalDto obj) {

        List<UsuarioAccion> favoritos = favoritoDao.findByUsuarioTipoAccion(usuarioLogueado.getId(), TipoAccion.FAVORITO.getId());

        if (!favoritos.isEmpty()) {
            ClienteSucursalDto clienteSucursalDto;

            for (UsuarioAccion favObj : favoritos) {
                try {
                    clienteSucursalDto = converter.getClienteSucursalDto(
                            (ClienteSucursal) em.createNamedQuery("ClienteSucursal.findById")
                                    .setParameter("id", Integer.valueOf(favObj.getIdRef()))
                                    .getSingleResult()
                    );
                    
                    if (obj.getId().equals(clienteSucursalDto.getId())) {
                        return true;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ClienteSucursalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
}
