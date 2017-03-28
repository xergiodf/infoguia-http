package com.minicubic.infoguiacore.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author xergio
 * @version 1
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientePublicacionDto {

    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    private String titulo;
    
    @Getter
    @Setter
    private String descripcionCorta;
    
    @Getter
    @Setter
    private String dirImagen;
    
    @Getter
    @Setter
    private String botonAccion;
    
    @Getter
    @Setter
    private Date fechaCreacion;
    
    @Getter
    @Setter
    private Date fechaDesde;
    
    @Getter
    @Setter
    private Date fechaHasta;
    
    @Getter
    @Setter
    private ClienteDto clienteDto;
    
    @Getter
    @Setter
    private TipoPublicacionDto tipoPublicacionDto;
    
    @Getter
    @Setter
    private EstadoPublicacionDto estadoPublicacionDto;
}
