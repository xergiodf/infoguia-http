package com.minicubic.infoguiahttp.util;

import com.minicubic.infoguiahttp.dto.ArchivoCabDto;
import com.minicubic.infoguiahttp.dto.ArchivoDetDto;
import com.minicubic.infoguiahttp.dto.TipoArchivoDto;
import com.minicubic.infoguiahttp.enums.TableReference;
import com.minicubic.infoguiahttp.enums.TipoArchivo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xergio
 * @version 2 - 20/04/2017
 */
public class Builder {
    
    public static ArchivoCabDto buildArchivo(TipoArchivo tipoArchivo, TableReference tableReference,
            String idRef, String fileName, String mimeType) {
        List<ArchivoDetDto> archivosDetDto = new ArrayList<>();
        ArchivoDetDto archivoDetDto = new ArchivoDetDto();
        ArchivoCabDto archivoCabDto = new ArchivoCabDto();
        
        TipoArchivoDto tipoArchivoDto = new TipoArchivoDto();
        tipoArchivoDto.setId(tipoArchivo.getId());
        
        //Seteamos la cabecera
        archivoCabDto.setColumnaRef(tableReference.getIdColumnName());
        archivoCabDto.setTablaRef(tableReference.getTableName());
        archivoCabDto.setIdRef(idRef);
        archivoCabDto.setTipoArchivoDto(tipoArchivoDto);
        
        // Seteamos el detalle
        archivoDetDto.setMimeType(mimeType);
        archivoDetDto.setNombre(fileName);
        archivoDetDto.setUbicacion(Constants.UPLOAD_DIR + tipoArchivo.getUploadDir());
        archivoDetDto.setUrl(Constants.PUBLIC_SERVER_URL + tipoArchivo.getUploadDir() + fileName);
        
        archivosDetDto.add(archivoDetDto);
        
        archivoCabDto.setArchivosDetDto(archivosDetDto);
        
        return archivoCabDto;
    }
}
