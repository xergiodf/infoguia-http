package com.minicubic.infoguiahttp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xergio
 * @version 1
 */
@Entity
@Table(name = "tipos_suscripciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSuscripcion.findAll", query = "SELECT t FROM TipoSuscripcion t")
    , @NamedQuery(name = "TipoSuscripcion.findById", query = "SELECT t FROM TipoSuscripcion t WHERE t.id = :id")
    , @NamedQuery(name = "TipoSuscripcion.findByDescripcion", query = "SELECT t FROM TipoSuscripcion t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoSuscripcion.findByAuditUsuario", query = "SELECT t FROM TipoSuscripcion t WHERE t.auditUsuario = :auditUsuario")
    , @NamedQuery(name = "TipoSuscripcion.findByAuditFechaInsert", query = "SELECT t FROM TipoSuscripcion t WHERE t.auditFechaInsert = :auditFechaInsert")
    , @NamedQuery(name = "TipoSuscripcion.findByAuditFechaUpdate", query = "SELECT t FROM TipoSuscripcion t WHERE t.auditFechaUpdate = :auditFechaUpdate")})
public class TipoSuscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "audit_usuario")
    private String auditUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "audit_fecha_insert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditFechaInsert;
    @Column(name = "audit_fecha_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditFechaUpdate;

    public TipoSuscripcion() {
    }

    public TipoSuscripcion(Integer id) {
        this.id = id;
    }

    public TipoSuscripcion(Integer id, String descripcion, Date auditFechaInsert) {
        this.id = id;
        this.descripcion = descripcion;
        this.auditFechaInsert = auditFechaInsert;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAuditUsuario() {
        return auditUsuario;
    }

    public void setAuditUsuario(String auditUsuario) {
        this.auditUsuario = auditUsuario;
    }

    public Date getAuditFechaInsert() {
        return auditFechaInsert;
    }

    public void setAuditFechaInsert(Date auditFechaInsert) {
        this.auditFechaInsert = auditFechaInsert;
    }

    public Date getAuditFechaUpdate() {
        return auditFechaUpdate;
    }

    public void setAuditFechaUpdate(Date auditFechaUpdate) {
        this.auditFechaUpdate = auditFechaUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSuscripcion)) {
            return false;
        }
        TipoSuscripcion other = (TipoSuscripcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiacore.model.TipoSuscripcion[ id=" + id + " ]";
    }

}
