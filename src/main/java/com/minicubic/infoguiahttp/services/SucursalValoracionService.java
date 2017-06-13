package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.SucursalValoracionDao;
import com.minicubic.infoguiacore.dto.SucursalValoracionCabDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

/**
 *
 * @author xergio
 * @version 1 - 12.06.2017
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SucursalValoracionService {
    
    @Inject
    private SucursalValoracionDao dao;

    /**
     *
     * @param id
     * @return
     */
    public SucursalValoracionCabDto getSucursalValoracionCab(Integer id) {
        return dao.getSucursalValoracionCab(id);
    }
    
    /**
     * 
     * @param idCliente
     * @return 
     */
    public List<SucursalValoracionCabDto> getSucursalValoracionesCabByCliente(Long idCliente) {
        return dao.getSucursalValoracionesCabByCliente(idCliente);
    }
    
    /**
     * 
     * @param idClienteSucursal
     * @return 
     */
    public List<SucursalValoracionCabDto> getSucursalValoracionesCabByClienteSucursal(Integer idClienteSucursal) {
        return dao.getSucursalValoracionesCabByClienteSucursal(idClienteSucursal);
    }
    
    /**
     *
     * @param sucursalValoracionCabDto
     * @return
     */
    public SucursalValoracionCabDto saveSucursalValoracionCab(SucursalValoracionCabDto sucursalValoracionCabDto) {
        return dao.saveSucursalValoracionCab(sucursalValoracionCabDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteSucursalValoracion(Integer id) {
        dao.deleteSucursalValoracion(id);
    }
}
