package com.minicubic.infoguiacore.dto;

import com.minicubic.infoguiacore.enums.TableReference;
import com.minicubic.infoguiacore.enums.TipoArchivo;
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
public class ClienteSucursalDto implements Archivable {
    
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nombreSucursal;

    @Getter
    @Setter
    private String direccionFisica;

    @Getter
    @Setter
    private String coordenadas;

    @Getter
    @Setter
    private ClienteDto clienteDto;
 
    @Getter
    @Setter
    private String telefonos;
 
    @Getter
    @Setter
    private String emails;
    
    @Getter
    @Setter
    private List<ArchivoDto> archivos;
    
    @Override
    @XmlTransient
    public TipoArchivo getTipoArchivo() {
        return TipoArchivo.CLIENTE_SUCURSAL;
    }

    @Override
    @XmlTransient
    public TableReference getTableReference() {
        return TableReference.CLIENTE_SUCURSAL;
    }
}
