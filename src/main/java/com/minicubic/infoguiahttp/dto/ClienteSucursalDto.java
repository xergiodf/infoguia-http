package com.minicubic.infoguiahttp.dto;

import com.minicubic.infoguiahttp.enums.TableReference;
import com.minicubic.infoguiahttp.enums.TipoArchivo;
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
    private String sitioWeb;
    
    @Getter
    @Setter
    private String horarioAtencion;
    
    @Getter
    @Setter
    private CiudadDto ciudadDto;
    
    @Getter
    @Setter
    private Boolean checkRatingUsuario;
    
    @Getter
    @Setter
    private Boolean checkRatingInfoguia;
    
    @Getter
    @Setter
    private Boolean usuarioFavorito;
    
    @Getter
    @Setter
    private SucursalValoracionCabDto valoracion;
    
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
