package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "tipos_horarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoHorario.findAll", query = "SELECT t FROM TipoHorario t"),
    @NamedQuery(name = "TipoHorario.findById", query = "SELECT t FROM TipoHorario t WHERE t.id = :id"),
    @NamedQuery(name = "TipoHorario.findByDescripcion", query = "SELECT t FROM TipoHorario t WHERE t.descripcion = :descripcion")})
public class TipoHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoHorario", fetch = FetchType.LAZY)
    private List<SucursalHorarioCab> sucursalHorariosCabList;

    public TipoHorario() {
    }

    public TipoHorario(Integer id) {
        this.id = id;
    }

    public TipoHorario(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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

    @XmlTransient
    public List<SucursalHorarioCab> getSucursalHorariosCabList() {
        return sucursalHorariosCabList;
    }

    public void setSucursalHorariosCabList(List<SucursalHorarioCab> sucursalHorariosCabList) {
        this.sucursalHorariosCabList = sucursalHorariosCabList;
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
        if (!(object instanceof TipoHorario)) {
            return false;
        }
        TipoHorario other = (TipoHorario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.TipoHorario[ id=" + id + " ]";
    }
    
}
