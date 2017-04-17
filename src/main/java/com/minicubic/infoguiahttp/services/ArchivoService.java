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
    public ArchivoCabDto getArchivoCab(Integer id) {
        return dao.getArchivoCab(id);
    }
    
    /**
     * 
     * @return 
     */
    public List<ArchivoCabDto> getArchivoCabs() {
        return dao.getArchivoCabs();
    }
    
    /**
     * 
     * @param archivoCabDto
     * @return 
     */
    public ArchivoCabDto saveArchivoCab(ArchivoCabDto archivoCabDto) {
        return dao.saveArchivoCab(archivoCabDto);
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteArchivoCab(Integer id) {
        dao.deleteArchivoCab(id);
    }
}
