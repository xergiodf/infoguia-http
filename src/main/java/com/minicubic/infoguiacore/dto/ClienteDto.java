package com.minicubic.infoguiacore.dto;

import com.minicubic.infoguiacore.enums.TableReference;
import com.minicubic.infoguiacore.enums.TipoArchivo;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
public class ClienteDto implements Archivable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String codigoCliente;

    @Getter
    @Setter
    private String nombreCompleto;
    
    @Getter
    @Setter
    private String nombreCorto;
    
    @Getter
    @Setter
    private String descripcionCompleta;

    @Getter
    @Setter
    private String descripcionCorta;

    @Getter
    @Setter
    private Date fechaAlta;

    @Getter
    @Setter
    private Date fechaInicio;
    
    @Getter
    @Setter
    private List<ArchivoDto> archivos;
    
    @Override
    @XmlTransient
    public TipoArchivo getTipoArchivo() {
        return TipoArchivo.CLIENTE_PERFIL;
    }

    @Override
    @XmlTransient
    public TableReference getTableReference() {
        return TableReference.CLIENTE;
    }
}
