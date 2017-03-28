/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sucursal_valoracion_cab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalValoracionCab.findAll", query = "SELECT s FROM SucursalValoracionCab s"),
    @NamedQuery(name = "SucursalValoracionCab.findById", query = "SELECT s FROM SucursalValoracionCab s WHERE s.id = :id"),
    @NamedQuery(name = "SucursalValoracionCab.findByPuntajeTotal", query = "SELECT s FROM SucursalValoracionCab s WHERE s.puntajeTotal = :puntajeTotal")})
public class SucursalValoracionCab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puntaje_total")
    private Float puntajeTotal;
    @JoinColumn(name = "id_cliente_sucursal", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteSucursal idClienteSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCabecera", fetch = FetchType.LAZY)
    private List<SucursalValoracionDet> sucursalValoracionDetList;

    public SucursalValoracionCab() {
    }

    public SucursalValoracionCab(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(Float puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public ClienteSucursal getIdClienteSucursal() {
        return idClienteSucursal;
    }

    public void setIdClienteSucursal(ClienteSucursal idClienteSucursal) {
        this.idClienteSucursal = idClienteSucursal;
    }

    @XmlTransient
    public List<SucursalValoracionDet> getSucursalValoracionDetList() {
        return sucursalValoracionDetList;
    }

    public void setSucursalValoracionDetList(List<SucursalValoracionDet> sucursalValoracionDetList) {
        this.sucursalValoracionDetList = sucursalValoracionDetList;
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
        if (!(object instanceof SucursalValoracionCab)) {
            return false;
        }
        SucursalValoracionCab other = (SucursalValoracionCab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.SucursalValoracionCab[ id=" + id + " ]";
    }
    
}
