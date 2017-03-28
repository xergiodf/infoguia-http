package com.minicubic.infoguiacore.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Response<T> {
    @Getter
    @Setter
    private Integer codigo;

    @Getter
    @Setter
    private String mensaje;

    @Getter
    @Setter
    private T data;

}
