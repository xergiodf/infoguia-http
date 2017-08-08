package com.minicubic.infoguiahttp.dto;

import com.minicubic.infoguiahttp.enums.TableReference;
import com.minicubic.infoguiahttp.enums.TipoArchivo;
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
public class ClientePublicacionDto implements Archivable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private TipoPublicacionDto tipoPublicacionDto;

    @Getter
    @Setter
    private ClienteDto clienteDto;

    @Getter
    @Setter
    private EstadoPublicacionDto estadoPublicacionDto;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String descripcionCorta;

    @Getter
    @Setter
    private String dirImagen;

    @Getter
    @Setter
    private String botonAccion;

    @Getter
    @Setter
    private Date fechaCreacion;

    @Getter
    @Setter
    private Date fechaDesde;

    @Getter
    @Setter
    private Date fechaHasta;

    @Getter
    @Setter
    private Integer tiempoMuestra;

    @Getter
    @Setter
    private List<ArchivoDto> archivos;

    @Getter
    @Setter
    private Boolean usuarioListaDeseo;

    @Override
    @XmlTransient
    public TipoArchivo getTipoArchivo() {
        return TipoArchivo.CLIENTE_PUBLICACION;
    }

    @Override
    @XmlTransient
    public TableReference getTableReference() {
        return TableReference.CLIENTE_PUBLICACION;
    }
}
