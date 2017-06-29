package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.ArchivoDao;
import com.minicubic.infoguiahttp.dto.Archivable;
import com.minicubic.infoguiahttp.dto.ArchivoCabDto;
import com.minicubic.infoguiahttp.dto.ArchivoDetDto;
import com.minicubic.infoguiahttp.util.Builder;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Util;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author xergio
 * @version 2 - 26/04/2017
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ArchivoService {

    @Inject
    private ArchivoDao dao;

    /**
     * 
     * @param archivoCabDto
     * @return 
     */
    private ArchivoCabDto getArchivo(ArchivoCabDto archivoCabDto) {
        return dao.getArchivo(archivoCabDto);
    }
    
    /**
     * Metodo que guarda un solo archivo para un registro.
     * Cuando ya existe un registro, elimina el archivo anterior y crea uno nuevo.
     * @param input
     * @param archivableDto
     * @return 
     */
    public ArchivoCabDto saveArchivo(MultipartFormDataInput input, Archivable archivableDto) {
        // Guardamos el archivo en disco
        Map<String, String> map = Util.saveFileToDisk(
                input, 
                Constants.UPLOAD_DIR + archivableDto.getTipoArchivo().getUploadDir(), 
                archivableDto.getId().toString()
        );
        
        // Construimos el archivo para guardar
        ArchivoCabDto archivoCabDto = Builder.buildArchivo(
                archivableDto.getTipoArchivo(), 
                archivableDto.getTableReference(), 
                archivableDto.getId().toString(), 
                map.get("fileName"), map.get("mimeType")
        );
        
        // Verificamos si ya existe un archivo, en ese caso lo borramos
        ArchivoCabDto archivoAux = getArchivo(archivoCabDto);
        if ( !Util.isEmpty(archivoAux) ) 
            Util.deleteFileFromDisk(archivoAux.getArchivosDetDto().iterator().next().getUbicacion(), 
                    archivoAux.getArchivosDetDto().iterator().next().getNombre());
        
        // Guardamos en la BD
        return dao.saveArchivoUnDetalle(archivoCabDto);
    }

    /**
     * Metodo que guarda multiples archivos para un registro
     * @param input
     * @param archivableDto
     * @return 
     */
    public ArchivoCabDto saveArchivoMultiple(MultipartFormDataInput input, Archivable archivableDto) {
        // Guardamos el archivo en disco
        Map<String, String> map = Util.saveFileToDisk(
                input, 
                Constants.UPLOAD_DIR + archivableDto.getTipoArchivo().getUploadDir(), 
                archivableDto.getId().toString()
        );
        
        // Construimos el archivo para guardar
        ArchivoCabDto archivoCabDto = Builder.buildArchivo(
                archivableDto.getTipoArchivo(), 
                archivableDto.getTableReference(), 
                archivableDto.getId().toString(), 
                map.get("fileName"), map.get("mimeType")
        );
        
        // Guardamos en la BD
        return dao.saveArchivoMultipleDetalle(archivoCabDto);
    }
    
    /**
     * 
     * @param idDetalle 
     */
    public void deleteArchivo(Integer idDetalle) {
        
        // Borramos el archivo fisicamente
        ArchivoDetDto archivoAux = dao.getArchivoDetalle(idDetalle);
        if ( !Util.isEmpty(archivoAux) ) 
            Util.deleteFileFromDisk(archivoAux.getUbicacion(), 
                    archivoAux.getNombre());
        
        // Borramos el registro de la BD
        dao.deleteArchivoDetalle(idDetalle);
    }
}
