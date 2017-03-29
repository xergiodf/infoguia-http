package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cliente_ofertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteOferta.findAll", query = "SELECT c FROM ClienteOferta c"), 
    @NamedQuery(name = "ClienteOferta.findById", query = "SELECT c FROM ClienteOferta c WHERE c.id = :id"), 
    @NamedQuery(name = "ClienteOferta.findByDescripcion", query = "SELECT c FROM ClienteOferta c WHERE c.descripcion = :descripcion"), 
    @NamedQuery(name = "ClienteOferta.findByAuditUsuario", query = "SELECT c FROM ClienteOferta c WHERE c.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "ClienteOferta.findByAuditFechaInsert", query = "SELECT c FROM ClienteOferta c WHERE c.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "ClienteOferta.findByAuditFechaUpdate", query = "SELECT c FROM ClienteOferta c WHERE c.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class ClienteOferta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @Column(name = "descripcion")
    @Getter
    @Setter
    private String descripcion;

    @JoinColumn(name = "id_tipo_oferta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private TipoOferta tipoOferta;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Cliente cliente;
    
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
