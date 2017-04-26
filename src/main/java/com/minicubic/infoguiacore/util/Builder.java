package com.minicubic.infoguiacore.util;

import com.minicubic.infoguiacore.dto.ArchivoCabDto;
import com.minicubic.infoguiacore.dto.ArchivoDetDto;
import com.minicubic.infoguiacore.dto.TipoArchivoDto;
import com.minicubic.infoguiacore.enums.TableReference;
import com.minicubic.infoguiacore.enums.TipoArchivo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xergio
 * @version 2 - 20/04/2017
 */
public class Builder {
    
    /**
     * Devuelve un ArchivoCabDto sin Detalle, para busquedas
     * @param idRef
     * @return 
     */
    public static ArchivoCabDto buildArchivoImagenPerfilUsuario(String idRef) {
        ArchivoCabDto archivoCabDto = new ArchivoCabDto();
        
        TipoArchivoDto tipoArchivoDto = new TipoArchivoDto();
        tipoArchivoDto.setId(TipoArchivo.PERFIL_USUARIO.getId());
        
        // Seteamos la cabecera
        archivoCabDto.setColumnaRef(TableReference.PERFIL_USUARIO.getIdColumnName());
        archivoCabDto.setTablaRef(TableReference.PERFIL_USUARIO.getTableName());
        archivoCabDto.setIdRef(idRef);
        archivoCabDto.setTipoArchivoDto(tipoArchivoDto);
        
        return archivoCabDto;
    }

    /**
     * Devuelve un ArchivoCabDto con Detalles, para guardar
     * @param idRef
     * @param fileName
     * @param mimeType
     * @return 
     */
    public static ArchivoCabDto buildArchivoImagenPerfilUsuario(String idRef, String fileName, String mimeType) {
        List<ArchivoDetDto> archivosDetDto = new ArrayList<>();
        ArchivoDetDto archivoDetDto = new ArchivoDetDto();
        ArchivoCabDto archivoCabDto = Builder.buildArchivoImagenPerfilUsuario(idRef);
        
        // Seteamos el detalle
        archivoDetDto.setMimeType(mimeType);
        archivoDetDto.setNombre(fileName);
        archivoDetDto.setUbicacion(Constants.IMG_PERFIL_USUARIO_UPLOAD_DIR);
        archivoDetDto.setUrl(Constants.IMG_PERFIL_USUARIO_SERVER_URL + fileName);
        
        archivosDetDto.add(archivoDetDto);
        
        archivoCabDto.setArchivosDetDto(archivosDetDto);
        
        return archivoCabDto;
    }
    
    /**
     * 
     * @param idRef
     * @return 
     */
    public static ArchivoCabDto buildArchivoImagenPortadaSucursal(String idRef) {
        ArchivoCabDto archivoCabDto = new ArchivoCabDto();
        
        TipoArchivoDto tipoArchivoDto = new TipoArchivoDto();
        tipoArchivoDto.setId(TipoArchivo.PORTADA_SUCURSAL_CLIENTE.getId());
        
        // Seteamos la cabecera
        archivoCabDto.setColumnaRef(TableReference.SUCURSAL_CLIENTE.getIdColumnName());
        archivoCabDto.setTablaRef(TableReference.SUCURSAL_CLIENTE.getTableName());
        archivoCabDto.setIdRef(idRef);
        archivoCabDto.setTipoArchivoDto(tipoArchivoDto);
        
        return archivoCabDto;
    }
    
    /**
     * 
     * @param idRef
     * @param fileName
     * @param mimeType
     * @return 
     */
    public static ArchivoCabDto buildArchivoImagenPortadaSucursal(String idRef, String fileName, String mimeType) {
        List<ArchivoDetDto> archivosDetDto = new ArrayList<>();
        ArchivoDetDto archivoDetDto = new ArchivoDetDto();
        ArchivoCabDto archivoCabDto = Builder.buildArchivoImagenPortadaSucursal(idRef);
        
        // Seteamos el detalle
        archivoDetDto.setMimeType(mimeType);
        archivoDetDto.setNombre(fileName);
        archivoDetDto.setUbicacion(Constants.IMG_PORTADA_SUCURSAL_UPLOAD_DIR);
        archivoDetDto.setUrl(Constants.IMG_PORTADA_SUCURSAL_SERVER_URL + fileName);
        
        archivosDetDto.add(archivoDetDto);
        
        archivoCabDto.setArchivosDetDto(archivosDetDto);
        
        return archivoCabDto;
    }
    
    /**
     * Devuelve un ArchivoCabDto sin Detalle, para busquedas
     * @param idRef
     * @return 
     */
    public static ArchivoCabDto buildArchivoImagenPublicacion(String idRef) {
        ArchivoCabDto archivoCabDto = new ArchivoCabDto();
        
        TipoArchivoDto tipoArchivoDto = new TipoArchivoDto();
        tipoArchivoDto.setId(TipoArchivo.IMAGEN_PUBLICACION.getId());
        
        // Seteamos la cabecera
        archivoCabDto.setColumnaRef(TableReference.PUBLICACION_CLIENTE.getIdColumnName());
        archivoCabDto.setTablaRef(TableReference.PUBLICACION_CLIENTE.getTableName());
        archivoCabDto.setIdRef(idRef);
        archivoCabDto.setTipoArchivoDto(tipoArchivoDto);
        
        return archivoCabDto;
    }

    /**
     * Devuelve un ArchivoCabDto con Detalles, para guardar
     * @param idRef
     * @param fileName
     * @param mimeType
     * @return 
     */
    public static ArchivoCabDto buildArchivoImagenPublicacion(String idRef, String fileName, String mimeType) {
        List<ArchivoDetDto> archivosDetDto = new ArrayList<>();
        ArchivoDetDto archivoDetDto = new ArchivoDetDto();
        ArchivoCabDto archivoCabDto = Builder.buildArchivoImagenPerfilUsuario(idRef);
        
        // Seteamos el detalle
        archivoDetDto.setMimeType(mimeType);
        archivoDetDto.setNombre(fileName);
        archivoDetDto.setUbicacion(Constants.IMG_PUBLICACION_UPLOAD_DIR);
        archivoDetDto.setUrl(Constants.IMG_PUBLICACION_SERVER_URL + fileName);
        
        archivosDetDto.add(archivoDetDto);
        
        archivoCabDto.setArchivosDetDto(archivosDetDto);
        
        return archivoCabDto;
    }
}
