package com.minicubic.infoguiahttp.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author sedf
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
public class UsuarioAccionDto {

    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    private UsuarioDto usuarioDto;
    
    @Getter
    @Setter
    private TipoAccionDto tipoAccionDto;

    @Getter
    @Setter
    private String palabraClave;
    
    @Getter
    @Setter
    private String tablaRef;
    
    @Getter
    @Setter
    private String colRef;
    
    @Getter
    @Setter
    private String idRef;
}
