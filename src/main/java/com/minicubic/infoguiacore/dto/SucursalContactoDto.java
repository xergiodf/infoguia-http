package com.minicubic.infoguiacore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author xergio
 * @version 1
 */
public class SucursalContactoDto {

    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    private String contacto;
    
    @Getter
    @Setter
    private TipoContactoDto tipoContactoDto;
    
    @Getter
    @Setter
    private ClienteSucursalDto clienteSucursalDto;
}
