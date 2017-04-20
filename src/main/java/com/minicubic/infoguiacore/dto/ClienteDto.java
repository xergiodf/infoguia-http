package com.minicubic.infoguiacore.dto;

import java.util.Date;
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
public class ClienteDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String codigoCliente;

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
    private Date fechaAlta;

    @Getter
    @Setter
    private Date fechaInicio;
}
