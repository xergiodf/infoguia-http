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
public class TipoHorarioDto { 
 
    @Getter 
    @Setter 
    private Integer id; 
     
    @Getter 
    @Setter 
    private String descripcion; 
    
    public enum TIPOS {
        
        NO_DISPONIBLE(1),
        SIEMPRE_ABIERTO(2),
        CERRADO_DEFINITIVAMENTE(3),
        DIAS_ESPECIFICOS(4);
        
        @Getter
        private Integer id;
        
        private TIPOS(Integer id) {
            this.id = id;
        }
    }
} 