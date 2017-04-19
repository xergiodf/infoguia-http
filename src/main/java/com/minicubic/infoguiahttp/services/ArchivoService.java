package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.ArchivoDao;
import com.minicubic.infoguiacore.dto.ArchivoCabDto;
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
 * @version 1 - 18/04/2017
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ArchivoService {

    @Inject
    private ArchivoDao dao;
    
    /**
     * 
     * @param id
     * @return 
     */
    public ArchivoCabDto getArchivo(Integer id) {
        return dao.getArchivo(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<ArchivoCabDto> getArchivos() {
        return dao.getArchivos();
    }
    
    /**
     * 
     * @param archivoCabDto
     * @return 
     */
    public ArchivoCabDto saveArchivo(ArchivoCabDto archivoCabDto) {
        return dao.saveArchivo(archivoCabDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteArchivo(Integer id) {
        dao.deleteArchivo(id);
    }
}
