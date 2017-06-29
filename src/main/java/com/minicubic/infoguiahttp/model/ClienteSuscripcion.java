package com.minicubic.infoguiahttp.model;

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
@Table(name = "cliente_suscripciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteSuscripcion.findAll", query = "SELECT c FROM ClienteSuscripcion c"), 
    @NamedQuery(name = "ClienteSuscripcion.findById", query = "SELECT c FROM ClienteSuscripcion c WHERE c.id = :id"), 
    @NamedQuery(name = "ClienteSuscripcion.findByFechaSuscripcion", query = "SELECT c FROM ClienteSuscripcion c WHERE c.fechaSuscripcion = :fechaSuscripcion"), 
    @NamedQuery(name = "ClienteSuscripcion.findByObservacion", query = "SELECT c FROM ClienteSuscripcion c WHERE c.observacion = :observacion"), 
    @NamedQuery(name = "ClienteSuscripcion.findByAuditUsuario", query = "SELECT c FROM ClienteSuscripcion c WHERE c.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "ClienteSuscripcion.findByAuditFechaInsert", query = "SELECT c FROM ClienteSuscripcion c WHERE c.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "ClienteSuscripcion.findByAuditFechaUpdate", query = "SELECT c FROM ClienteSuscripcion c WHERE c.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class ClienteSuscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @Column(name = "fecha_suscripcion")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechaSuscripcion;

    @Column(name = "observacion")
    @Getter
    @Setter
    private String observacion;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Cliente cliente;
    
    @JoinColumn(name = "id_estado_suscripcion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private EstadoSuscripcion estadoSuscripcion;

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
