package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Entity
@Table(name = "estado_suscripciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSuscripcion.findAll", query = "SELECT e FROM EstadoSuscripcion e"), 
    @NamedQuery(name = "EstadoSuscripcion.findById", query = "SELECT e FROM EstadoSuscripcion e WHERE e.id = :id"), 
    @NamedQuery(name = "EstadoSuscripcion.findByDescripcion", query = "SELECT e FROM EstadoSuscripcion e WHERE e.descripcion = :descripcion"), 
    @NamedQuery(name = "EstadoSuscripcion.findByAuditUsuario", query = "SELECT e FROM EstadoSuscripcion e WHERE e.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "EstadoSuscripcion.findByAuditFechaInsert", query = "SELECT e FROM EstadoSuscripcion e WHERE e.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "EstadoSuscripcion.findByAuditFechaUpdate", query = "SELECT e FROM EstadoSuscripcion e WHERE e.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class EstadoSuscripcion implements Serializable {
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

    @Column(name = "audit_usuario")
    @Getter
    @Setter
    private String auditUsuario;

    @Column(name = "audit_fecha_insert")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaInsert;
    
    @Column(name = "audit_fecha_update")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaUpdate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoSuscripcion", fetch = FetchType.LAZY)
    private Collection<ClienteSuscripcion> clienteSuscripciones;

    @XmlTransient
    public Collection<ClienteSuscripcion> getClienteSuscripciones() {
        return clienteSuscripciones;
    }

    public void setClienteSuscripciones(Collection<ClienteSuscripcion> clienteSuscripciones) {
        this.clienteSuscripciones = clienteSuscripciones;
    }
}
