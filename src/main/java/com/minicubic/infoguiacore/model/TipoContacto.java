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
@Table(name = "tipos_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContacto.findAll", query = "SELECT t FROM TipoContacto t"),
    @NamedQuery(name = "TipoContacto.findById", query = "SELECT t FROM TipoContacto t WHERE t.id = :id"),
    @NamedQuery(name = "TipoContacto.findByDescripcion", query = "SELECT t FROM TipoContacto t WHERE t.descripcion = :descripcion")})
public class TipoContacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoContacto", fetch = FetchType.LAZY)
    private List<SucursalContacto> sucursalContactosList;

    public TipoContacto() {
    }

    public TipoContacto(Integer id) {
        this.id = id;
    }

    public TipoContacto(Integer id, String descripcion) {
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
    public List<SucursalContacto> getSucursalContactosList() {
        return sucursalContactosList;
    }

    public void setSucursalContactosList(List<SucursalContacto> sucursalContactosList) {
        this.sucursalContactosList = sucursalContactosList;
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
        if (!(object instanceof TipoContacto)) {
            return false;
        }
        TipoContacto other = (TipoContacto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.TipoContacto[ id=" + id + " ]";
    }
    
}
