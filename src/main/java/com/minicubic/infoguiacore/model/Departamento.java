package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xergio
 * @version 1 - 09.05.2017
 */
@Entity
@Table(name = "departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findById", query = "SELECT d FROM Departamento d WHERE d.id = :id")
    , @NamedQuery(name = "Departamento.findByDescripcion", query = "SELECT d FROM Departamento d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Departamento.findByAuditUsuario", query = "SELECT d FROM Departamento d WHERE d.auditUsuario = :auditUsuario")
    , @NamedQuery(name = "Departamento.findByAuditFechaInsert", query = "SELECT d FROM Departamento d WHERE d.auditFechaInsert = :auditFechaInsert")
    , @NamedQuery(name = "Departamento.findByAuditFechaUpdate", query = "SELECT d FROM Departamento d WHERE d.auditFechaUpdate = :auditFechaUpdate")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamento")
    private List<Ciudad> ciudadList;

    public Departamento() {
    }

    public Departamento(Integer id) {
        this.id = id;
    }

    public Departamento(Integer id, String descripcion, Date auditFechaInsert) {
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

    @XmlTransient
    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
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
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiacore.model.Departamento[ id=" + id + " ]";
    }

}
