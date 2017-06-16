package com.minicubic.infoguiacore.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author sedf
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
public class SucursalCategoriaDto {
    
    @Getter
    @Setter
    private ClienteSucursalDto sucursalDto;
    
    @Getter
    @Setter
    private List<CategoriaDto> categoriasDto;
    
}
