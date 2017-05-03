package com.minicubic.infoguiacore.dto;

import com.minicubic.infoguiacore.enums.TableReference;
import com.minicubic.infoguiacore.enums.TipoArchivo;
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
