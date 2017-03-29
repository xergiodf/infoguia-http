package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "usuario_mensajes_cab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioMensajeCab.findAll", query = "SELECT u FROM UsuarioMensajeCab u"),
    @NamedQuery(name = "UsuarioMensajeCab.findById", query = "SELECT u FROM UsuarioMensajeCab u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioMensajeCab.findByFechaMensaje", query = "SELECT u FROM UsuarioMensajeCab u WHERE u.fechaMensaje = :fechaMensaje"),
    @NamedQuery(name = "UsuarioMensajeCab.findByNroOrden", query = "SELECT u FROM UsuarioMensajeCab u WHERE u.nroOrden = :nroOrden")})
@ToString
@EqualsAndHashCode
public class UsuarioMensajeCab implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @JoinColumn(name = "id_usuario_emisor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Usuario usuarioEmisor;
    
    @JoinColumn(name = "id_usuario_receptor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Usuario usuarioReceptor;
    
    @Column(name = "fecha_mensaje")
    @Getter
    @Setter
    private String fechaMensaje;
    
    @Column(name = "nro_orden")
    @Getter
    @Setter
    private Integer nroOrden;

    @Column(name = "mensaje")
    @Getter
    @Setter
    private String mensaje;

    @Column(name = "audit_usuario")
    @Getter
    @Setter
    private String auditUsuario;

    @Column(name = "audit_fecha_insert", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaInsert;
    
    @Column(name = "audit_fecha_update", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaUpdate;
}
