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
 * @version 1 - 23.07.2017
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
public class SearchRequestDto {
    
    @Getter
    @Setter
    private String query;
    
    @Getter
    @Setter
    private Integer cityId;
    
    @Getter
    @Setter
    private Integer categoryId;
    
    @Getter
    @Setter
    private Integer page;
}
