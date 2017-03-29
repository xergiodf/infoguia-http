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
 * @author xergio
 * @version 1
 */
@Entity
@Table(name = "cliente_publicaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientePublicacion.findAll", query = "SELECT c FROM ClientePublicacion c"),
    @NamedQuery(name = "ClientePublicacion.findById", query = "SELECT c FROM ClientePublicacion c WHERE c.id = :id"),
    @NamedQuery(name = "ClientePublicacion.findByTitulo", query = "SELECT c FROM ClientePublicacion c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "ClientePublicacion.findByDescripcionCorta", query = "SELECT c FROM ClientePublicacion c WHERE c.descripcionCorta = :descripcionCorta"),
    @NamedQuery(name = "ClientePublicacion.findByFechaCreacion", query = "SELECT c FROM ClientePublicacion c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ClientePublicacion.findByFechaDesde", query = "SELECT c FROM ClientePublicacion c WHERE c.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "ClientePublicacion.findByFechaHasta", query = "SELECT c FROM ClientePublicacion c WHERE c.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "ClientePublicacion.findByTipoPublicacion", query = "SELECT c FROM ClientePublicacion c WHERE c.tipoPublicacion.id = :tipoPublicacionId"),
    @NamedQuery(name = "ClientePublicacion.findByCliente", query = "SELECT c FROM ClientePublicacion c WHERE c.cliente.id = :clienteId")})
@ToString
@EqualsAndHashCode
public class ClientePublicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @JoinColumn(name = "id_tipo_publicacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private TipoPublicacion tipoPublicacion;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Cliente cliente;
    
    @JoinColumn(name = "id_estado_publicacion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private EstadoPublicacion estadoPublicacion;
    
    @Column(name = "titulo")
    @Getter
    @Setter
    private String titulo;
    
    @Column(name = "descripcion_corta")
    @Getter
    @Setter
    private String descripcionCorta;
    
    @Column(name = "dir_imagen")
    @Getter
    @Setter
    private String dirImagen;
    
    @Column(name = "boton_accion")
    @Getter
    @Setter
    private String botonAccion;
    
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechaCreacion;

    @Column(name = "fecha_desde")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechaDesde;
    
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechaHasta;
    
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
