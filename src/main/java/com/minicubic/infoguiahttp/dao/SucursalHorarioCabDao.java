package com.minicubic.infoguiahttp.dao;

import com.minicubic.infoguiahttp.model.SucursalHorarioCab;
import java.util.HashMap;
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
public class SucursalHorarioCabDao extends GenericDao<SucursalHorarioCab> {
    
    @PersistenceContext(unitName = "infoGuiaPU")
    private EntityManager em;
    
    public SucursalHorarioCabDao() {
        super(SucursalHorarioCab.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * 
     * @param idSucursal
     * @return 
     */
    public SucursalHorarioCab findBySucursal(Integer idSucursal) {
        Map<String, Object> params = new HashMap<>();
        params.put("idSucursal", idSucursal);
        return findSingleWithNamedQuery("SucursalHorarioCab.findBySucursal", params);
    }
    
}
