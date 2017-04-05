package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.Usuario;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiacore.util.converter.UsuarioConverter;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xergio
 * @version 1
 */
public class AuthDao {

    private final UsuarioConverter converter = new UsuarioConverter();
    private static final Logger LOG = Logger.getLogger("AuthDao");

    @PersistenceContext(unitName = "infoGuiaPU")
    private EntityManager em;

    /**
     *
     * @param usuarioParam
     * @return
     */
    public UsuarioDto getUsuarioByParam(UsuarioDto usuarioParam) {
        try {

            Usuario usuario = null;

            if (!Util.isEmpty(usuarioParam.getUsername())) {
                usuario = (Usuario) em.createNamedQuery("Usuario.findByUsername")
                        .setParameter("username", usuarioParam.getUsername())
                        .getSingleResult();
            }

            if (!Util.isEmpty(usuarioParam.getEmail())) {
                usuario = (Usuario) em.createNamedQuery("Usuario.findByEmail")
                        .setParameter("email", usuarioParam.getEmail())
                        .getSingleResult();
            }

            if (!Util.isEmpty(usuarioParam.getUsername())
                    && !Util.isEmpty(usuarioParam.getTokenConfirmacion())) {
                usuario = (Usuario) em.createNamedQuery("Usuario.finfByUsernameAndTokenConfirmacion")
                        .setParameter("username", usuarioParam.getUsername())
                        .setParameter("tokenConfirmacion", usuarioParam.getTokenConfirmacion())
                        .setParameter("estadoUsuarioId", Constants.DB_USR_ESTADO_FALTA_ACTIVAR_ID)
                        .getSingleResult();
            }

            return converter.getUsuarioDto(usuario);

        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     * @param user
     * @param pass
     * @return
     */
    public UsuarioDto getUsuarioByCredentials(String user, String pass) {
        try {

            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByUsernameAndPassword")
                    .setParameter("username", user)
                    .setParameter("password", pass)
                    .setParameter("estadoUsuarioId", Constants.DB_USR_ESTADO_ACTIVO_ID)
                    .getSingleResult();

            return converter.getUsuarioDto(usuario);

        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param usuarioDto
     * @return
     */
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        try {
            Usuario usuario = converter.getUsuario(usuarioDto);
            
            if ( !Util.isEmpty(usuario.getId()) && Util.isEmpty(usuario.getPassword()) ) {
                Usuario usuarioAux = (Usuario) em.createNamedQuery("Usuario.findById")
                    .setParameter("id", usuarioDto.getId())
                    .getSingleResult();
                usuario.setPassword(usuarioAux.getPassword());
            }

            usuario.setAuditUsuario(usuarioDto.getUsername());
            usuario = em.merge(usuario);
            em.flush();

            return converter.getUsuarioDto(usuario);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
