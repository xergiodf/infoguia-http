package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.model.SucursalHorarioDet;
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
public class SucursalHorarioDetDao extends GenericDao<SucursalHorarioDet> {
    
    @PersistenceContext(unitName = "infoGuiaPU")
    private EntityManager em;
    
    public SucursalHorarioDetDao() {
        super(SucursalHorarioDet.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
