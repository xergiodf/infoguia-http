package com.minicubic.infoguiahttp.dto;

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
public class ClienteOfertaDto {
    
    @Getter
    @Setter
    private Integer id;
    
    @Column(name = "descripcion")
    @Getter
    @Setter
    private String descripcion;

    @Getter
    @Setter
    private TipoOfertaDto tipoOfertaDto;
    
    @Getter
    @Setter
    private ClienteDto clienteDto;
}
