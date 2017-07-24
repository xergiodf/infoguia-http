package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.model.UsuarioAccion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sedf
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UsuarioAccionDao extends GenericDao<UsuarioAccion> {
    @PersistenceContext(unitName = "infoGuiaPU")
    private EntityManager em;
    
    public UsuarioAccionDao() {
        super(UsuarioAccion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<UsuarioAccion> findByAllParams(UsuarioAccion usuarioAccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("idUsuario", usuarioAccion.getUsuario().getId());
        params.put("idTipoAccion", usuarioAccion.getTipoAccion().getId());
        params.put("tablaRef", usuarioAccion.getTablaRef());
        params.put("colRef", usuarioAccion.getColRef());
        params.put("idRef", usuarioAccion.getIdRef());
        return findWithNamedQuery("UsuarioAccion.findByAllParams", params, 0);
    }
    
    public List<UsuarioAccion> findByUsuarioTipoAccion(Long idUsuario, Integer idTipoAccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("idUsuario", idUsuario);
        params.put("idTipoAccion", idTipoAccion);
        return findWithNamedQuery("UsuarioAccion.findByUsuarioTipoAccion", params, 0);
    }
}
