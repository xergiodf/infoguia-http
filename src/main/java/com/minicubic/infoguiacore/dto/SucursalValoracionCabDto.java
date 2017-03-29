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
public class SucursalValoracionCabDto {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private ClienteSucursalDto clienteSucursalDto;

    @Getter
    @Setter
    private Float puntajeTotal;
}
