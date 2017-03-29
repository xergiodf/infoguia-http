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
public class UsuarioDto {

    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String email;
    
    @Getter
    @Setter
    private String username;
    
    @Getter
    @Setter
    private String password;
    
    @Getter
    @Setter
    private Date fechaRegistro;
    
    @Getter
    @Setter
    private Date fechaExpiracion;
    
    @Getter
    @Setter
    private String tokenCambioPass;
    
    @Getter
    @Setter
    private String tokenConfirmacion;
    
    @Getter
    @Setter
    private ClienteDto clienteDto;
    
    @Getter
    @Setter
    private TipoUsuarioDto tipoUsuarioDto;
    
    @Getter
    @Setter
    private EstadoUsuarioDto usuarioEstadoDto;
    
    @Getter
    @Setter
    private String tokenAuth;
    
    @Getter
    @Setter
    private String newPassword;
    
    @Getter
    @Setter
    private Boolean admin;
}
