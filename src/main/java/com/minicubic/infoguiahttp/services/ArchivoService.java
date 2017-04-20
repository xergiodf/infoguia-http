package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiacore.dao.ArchivoDao;
import com.minicubic.infoguiacore.dto.ArchivoCabDto;
import com.minicubic.infoguiacore.util.Builder;
import com.minicubic.infoguiacore.util.Constants;
import com.minicubic.infoguiacore.util.Util;
import java.io.IOException;
import java.util.List;
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
     * @param archivoCabDto
     * @return 
     */
    private ArchivoCabDto getArchivo(ArchivoCabDto archivoCabDto) {
        return dao.getArchivo(archivoCabDto);
    }

    /**
     * Obtiene la URL de la imagen de perfil de usuario.
     * Sino existe, retorna la imagen por defecto.
     * @param idRef
     * @return 
     */
    public String getUrlImagenPerfilUsuario(String idRef) {
        // Construimos el archivo para buscar
        ArchivoCabDto archivoPerfilUsuario = Builder.buildArchivoImagenPerfilUsuario(idRef);
        
        // Verificamos si existe
        ArchivoCabDto archivoAux = getArchivo(archivoPerfilUsuario);
        if ( !Util.isEmpty(archivoAux) ) 
            return archivoAux.getArchivosDetDto().iterator().next().getUrl();
        else
            return Constants.IMG_PERFIL_USUARIO_SERVER_URL + Constants.IMG_PERFIL_USUARIO_DEFAULT;
    }

    /**
     * 
     * @param input
     * @param idRef
     * @return 
     * @throws java.io.IOException 
     */
    public ArchivoCabDto saveArchivoImagenPerfilUsuario(MultipartFormDataInput input, String idRef) throws IOException {
        // Guardamos el archivo en disco
        Map<String, String> map = Util.saveFileToDisk(input, Constants.IMG_PERFIL_USUARIO_UPLOAD_DIR, idRef);
        
        // Construimos el archivo para guardar
        ArchivoCabDto archivoImagenPerfilUsuario = Builder.buildArchivoImagenPerfilUsuario(idRef, map.get("fileName"), map.get("mimeType"));
        
        // Verificamos si ya existe un archivo, en ese caso lo borramos
        ArchivoCabDto archivoAux = getArchivo(archivoImagenPerfilUsuario);
        if ( !Util.isEmpty(archivoAux) ) 
            Util.deleteFileFromDisk(archivoAux.getArchivosDetDto().iterator().next().getUbicacion(), 
                    archivoAux.getArchivosDetDto().iterator().next().getNombre());

        // Guardamos en la BD
        return dao.saveArchivoUnDetalle(archivoImagenPerfilUsuario);
    }
    
    /**
     * 
     * @param input
     * @param idRef
     * @return
     * @throws IOException 
     */
    public ArchivoCabDto saveArchivoImagenPortadaSucursal(MultipartFormDataInput input, String idRef) throws IOException {
        // Guardamos el archivo en disco
        Map<String, String> map = Util.saveFileToDisk(input, Constants.IMG_PORTADA_SUCURSAL_UPLOAD_DIR, idRef);
        
        // Construimos el archivo para guardar
        ArchivoCabDto archivoImagenPortadaSucursal = Builder.buildArchivoImagenPortadaSucursal(idRef, map.get("fileName"), map.get("mimeType"));
        
        // Verificamos si ya existe un archivo, en ese caso lo borramos
        ArchivoCabDto archivoAux = getArchivo(archivoImagenPortadaSucursal);
        if ( !Util.isEmpty(archivoAux) ) 
            Util.deleteFileFromDisk(archivoAux.getArchivosDetDto().iterator().next().getUbicacion(), 
                    archivoAux.getArchivosDetDto().iterator().next().getNombre());

        // Guardamos en la BD
        return dao.saveArchivoUnDetalle(archivoImagenPortadaSucursal);
    }
}
