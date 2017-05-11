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
public class UsuarioPerfilDto implements Archivable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nombres;

    @Getter
    @Setter
    private String apellidos;

    @Getter
    @Setter
    private Date fechaNacimiento;

    @Getter
    @Setter
    private String ocupacion;

    @Getter
    @Setter
    private Integer ciudad;

    @Getter
    @Setter
    private UsuarioDto usuarioDto;
    
    @Getter
    @Setter
    private List<ArchivoDto> archivos;
    
    @Override
    @XmlTransient
    public TipoArchivo getTipoArchivo() {
        return TipoArchivo.USUARIO_PERFIL;
    }

    @Override
    @XmlTransient
    public TableReference getTableReference() {
        return TableReference.USUARIO_PERFIL;
    }
}
