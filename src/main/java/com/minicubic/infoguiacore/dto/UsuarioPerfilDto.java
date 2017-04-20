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
public class UsuarioPerfilDto {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nombres;

    @Getter
    @Setter
    private String apellidos;

    @Getter
    @Setter
    private Date fechaNacimiento;

    @Getter
    @Setter
    private String ocupacion;

    @Getter
    @Setter
    private Integer ciudad;

    @Getter
    @Setter
    private UsuarioDto usuarioDto;
    
    @Getter
    @Setter
    private String imagenPerfil;
}
