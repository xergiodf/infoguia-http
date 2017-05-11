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
 * @version 1 - 18/04/2017
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
public class ArchivoDetDto {

    @Getter
    @Setter
    private Integer id;
    
    @Getter
    @Setter
    private String mimeType;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String ubicacion;

    @Getter
    @Setter
    private String url;
}
