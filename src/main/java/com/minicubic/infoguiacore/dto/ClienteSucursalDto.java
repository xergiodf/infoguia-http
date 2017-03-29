package com.minicubic.infoguiacore.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author xergio
 * @version 1
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
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
 
    @Getter
    @Setter
    private String telefonos;
 
    @Getter
    @Setter
    private String emails;
}
