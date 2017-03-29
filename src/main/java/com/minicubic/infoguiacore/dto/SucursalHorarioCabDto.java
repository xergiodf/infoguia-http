package com.minicubic.infoguiacore.dto;

import javax.persistence.Column;
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
public class SucursalHorarioCabDto {

    @Column(name = "id")
    private Integer id;

    @Getter
    @Setter
    private TipoHorarioDto tipoHorarioDto;

    @Getter
    @Setter
    private ClienteSucursalDto clienteSucursalDto;
}
