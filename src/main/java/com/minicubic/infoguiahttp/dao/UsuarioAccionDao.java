package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.model.UsuarioAccion;
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
}
