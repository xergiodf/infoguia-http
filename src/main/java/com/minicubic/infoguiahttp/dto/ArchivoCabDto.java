package com.minicubic.infoguiahttp.dto;

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
 * @author xergio
 * @version 1 - 17/04/2017
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
public class ArchivoCabDto {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String tablaRef;

    @Getter
    @Setter
    private String columnaRef;

    @Getter
    @Setter
    private String idRef;

    @Getter
    @Setter
    private TipoArchivoDto tipoArchivoDto;
    
    @Getter
    @Setter
    private List<ArchivoDetDto> archivosDetDto;
}
