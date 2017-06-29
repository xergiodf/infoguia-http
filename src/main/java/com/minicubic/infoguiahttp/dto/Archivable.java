package com.minicubic.infoguiahttp.dto;

import com.minicubic.infoguiahttp.enums.TableReference;
import com.minicubic.infoguiahttp.enums.TipoArchivo;
import java.util.List;

/**
 *
 * @author xergio
 * @version 1 - 02.05.2017
 */
public interface Archivable {

    public Object getId();
    
    public TipoArchivo getTipoArchivo();
    
    public TableReference getTableReference();
    
    public List<ArchivoDto> getArchivos();
}
