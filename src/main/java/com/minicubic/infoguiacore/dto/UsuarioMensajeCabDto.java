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
public class UsuarioMensajeCabDto {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private UsuarioDto usuarioEmisorDto;

    @Getter
    @Setter
    private UsuarioDto usuarioReceptorDto;

    @Getter
    @Setter
    private String fechaMensaje;

    @Getter
    @Setter
    private Integer nroOrden;

    @Getter
    @Setter
    private String mensaje;
}
