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
 * @version 1 - 19/04/2017
 */
public class Builder {

    public static ArchivoCabDto buildArchivoFotoPerfil(String idRef, String fileName, String mimeType) {
        List<ArchivoDetDto> archivosDetDto = new ArrayList<>();
        ArchivoCabDto archivoCabDto = new ArchivoCabDto();
        ArchivoDetDto archivoDetDto = new ArchivoDetDto();
        
        TipoArchivoDto tipoArchivoDto = new TipoArchivoDto();
        tipoArchivoDto.setId(TipoArchivo.PERFIL_USUARIO.getId());
        
        // Seteamos la cabecera
        archivoCabDto.setColumnaRef(TableReference.PERFIL_USUARIO.getIdColumnName());
        archivoCabDto.setTablaRef(TableReference.PERFIL_USUARIO.getTableName());
        archivoCabDto.setIdRef(idRef);
        archivoCabDto.setTipoArchivoDto(tipoArchivoDto);
        
        // Seteamos el detalle
        archivoDetDto.setMimeType(mimeType);
        archivoDetDto.setNombre(fileName);
        archivoDetDto.setUbicacion(Constants.UPLOAD_DIR);
        archivoDetDto.setUrl(Constants.PUBLIC_SERVER_URL + "/" + fileName);
        
        archivosDetDto.add(archivoDetDto);
        
        archivoCabDto.setArchivosDetDto(archivosDetDto);
        
        return archivoCabDto;
    }
}
