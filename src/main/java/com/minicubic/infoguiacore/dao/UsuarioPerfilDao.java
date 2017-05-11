package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.UsuarioPerfilDto;
import com.minicubic.infoguiacore.enums.TableReference;
import com.minicubic.infoguiacore.model.UsuarioPerfil;
import com.minicubic.infoguiacore.util.converter.ArchivoConverter;
import com.minicubic.infoguiacore.util.converter.UsuarioPerfilConverter;
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
public class UsuarioPerfilDao {
    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;
    
    @Inject
    private ArchivoDao archivoDao;

    private final UsuarioPerfilConverter converter = new UsuarioPerfilConverter();
    private final ArchivoConverter archivoConverter = new ArchivoConverter();
    private static final Logger LOG = Logger.getLogger("UsuarioDao");
    
    @PersistenceContext(unitName="infoGuiaPU")
    private EntityManager em;
    
    /**
     *
     * @param id
     * @return
     */
    public UsuarioPerfilDto getUsuarioPerfil(Integer id) {
        try {            
            UsuarioPerfilDto usuarioPerfilDto = converter.getUsuarioPerfilDto(
                    (UsuarioPerfil) em.createNamedQuery("UsuarioPerfil.findById")
                        .setParameter("id", id)
                        .getSingleResult()
            );
            
            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            usuarioPerfilDto.setArchivos(
                    archivoConverter.getArchivoDto(
                            archivoDao.getArchivo(
                                    TableReference.USUARIO_PERFIL.getTableName(),
                                    TableReference.USUARIO_PERFIL.getIdColumnName(),
                                    id.toString()
                            )
                    )
            );

            return usuarioPerfilDto;
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param idUsuario
     * @return
     */
    public UsuarioPerfilDto getUsuarioPerfilByUsuario(Long idUsuario) {
        try {            
            UsuarioPerfilDto usuarioPerfilDto = converter.getUsuarioPerfilDto(
                    (UsuarioPerfil) em.createNamedQuery("UsuarioPerfil.findByUsuario")
                        .setParameter("idUsuario", idUsuario)
                        .getSingleResult()
            );
            
            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            usuarioPerfilDto.setArchivos(
                    archivoConverter.getArchivoDto(
                            archivoDao.getArchivo(
                                    TableReference.USUARIO_PERFIL.getTableName(),
                                    TableReference.USUARIO_PERFIL.getIdColumnName(),
                                    idUsuario.toString()
                            )
                    )
            );

            return usuarioPerfilDto;
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
    public List<UsuarioPerfilDto> getUsuarioPerfiles() {
        try {
            List<UsuarioPerfilDto> usuarioPerfilesDto = converter.getUsuarioPerfilesDto(
                    em.createNamedQuery("UsuarioPerfil.findAll").getResultList()
            );

            // TODO: Buscar algun patron de disenho que mejore esto
            // Cargamos las imagenes (si tiene)
            for ( UsuarioPerfilDto usuarioPerfilDto : usuarioPerfilesDto ) {        
                usuarioPerfilDto.setArchivos(
                        archivoConverter.getArchivoDto(
                                archivoDao.getArchivo(
                                        TableReference.USUARIO_PERFIL.getTableName(),
                                        TableReference.USUARIO_PERFIL.getIdColumnName(),
                                        usuarioPerfilDto.getId().toString()
                                )
                        )
                );
            }
            
            return usuarioPerfilesDto;
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param usuarioPerfilDto
     * @return
     */
    public UsuarioPerfilDto saveUsuarioPerfil(UsuarioPerfilDto usuarioPerfilDto) {
        try {
            UsuarioPerfil usuarioPerfil = converter.getUsuarioPerfil(usuarioPerfilDto);

            usuarioPerfil.setAuditUsuario(usuarioLogueado.getUsername());
            usuarioPerfil = em.merge(usuarioPerfil);
            em.flush();

            return converter.getUsuarioPerfilDto(usuarioPerfil);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * 
     * @param id 
     */
    public void deleteUsuarioPerfil(Integer id) {
        try {
            UsuarioPerfil usuarioPerfil = (UsuarioPerfil) em.createNamedQuery("UsuarioPerfil.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.remove(usuarioPerfil);
            em.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
