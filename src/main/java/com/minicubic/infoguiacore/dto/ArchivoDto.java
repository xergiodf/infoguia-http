package com.minicubic.infoguiacore.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO exclusivo para ser incrustado en las clases que implementan la interfaz
 * Archivable
 * @author xergio
 * @version 1 - 02.05.2017
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
public class ArchivoDto {
    
    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    private String url;
}
