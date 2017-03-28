package com.minicubic.infoguiacore.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xergio
 * @author hectorvillalba
 * @version 2
 */

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClienteDto {
    
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String nombreCompleto;
    
    @Getter
    @Setter
    private String nombreCorto;
    
    @Getter
    @Setter
    private String descripcionCompleta;
    
    @Getter
    @Setter
    private String descripcionCorta;
    
    @Getter
    @Setter
    private Date fechAlta;
    
    @Getter
    @Setter
    private Date fechaInicio;
    
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
    private String horarios;
    @Getter
    @Setter
    private String telefono;
}
