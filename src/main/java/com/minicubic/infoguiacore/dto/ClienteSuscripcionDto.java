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
public class ClienteSuscripcionDto {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Date fechaSuscripcion;

    @Getter
    @Setter
    private String observacion;

    @Getter
    @Setter
    private ClienteDto clienteDto;

    @Getter
    @Setter
    private EstadoSuscripcionDto estadoSuscripcionDto;
}
