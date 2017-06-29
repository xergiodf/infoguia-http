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
 * @author xergio 
 * @version 1 
 */ 
@XmlRootElement 
@XmlAccessorType(XmlAccessType.FIELD) 
@ToString 
@EqualsAndHashCode 
public class SucursalHorarioCabDto { 
 
    @Getter 
    @Setter 
    private Integer id; 
 
    @Getter 
    @Setter 
    private TipoHorarioDto tipoHorarioDto; 
 
    @Getter 
    @Setter 
    private ClienteSucursalDto clienteSucursalDto; 
} 