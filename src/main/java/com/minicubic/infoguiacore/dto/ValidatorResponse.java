package com.minicubic.infoguiacore.dto;

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
public class ValidatorResponse<T> {
    
    public ValidatorResponse() {
        this.mensaje = "";
    }

    @Getter
    @Setter
    private String mensaje;

    @Getter
    @Setter
    private T data;
}
