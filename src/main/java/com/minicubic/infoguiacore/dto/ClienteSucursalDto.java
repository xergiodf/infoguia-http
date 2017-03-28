package com.minicubic.infoguiacore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author xergio
 * @version 1
 */
public class ClienteSucursalDto {

    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    private String nombreSucursal;
    
    @Getter
    @Setter
    private String direccionFisica;
    
    @Getter
    @Setter
    private String coordenadas;
    
    @Getter
    @Setter
    private ClienteDto clienteDto;
}
