package com.minicubic.infoguiacore.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Request<T> {
    
    @Getter
    @Setter
    private String type;
    
    @Getter
    @Setter
    private T data;
}
