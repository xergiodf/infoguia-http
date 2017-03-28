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
@Table(name = "sucursal_horarios_cab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalHorarioCab.findAll", query = "SELECT s FROM SucursalHorarioCab s"),
    @NamedQuery(name = "SucursalHorarioCab.findById", query = "SELECT s FROM SucursalHorarioCab s WHERE s.id = :id")})
public class SucursalHorarioCab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_cliente_sucursal", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteSucursal idClienteSucursal;
    @JoinColumn(name = "id_tipo_horario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoHorario idTipoHorario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCabecera", fetch = FetchType.LAZY)
    private List<SucursalHorarioDet> sucursalHorariosDetList;

    public SucursalHorarioCab() {
    }

    public SucursalHorarioCab(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteSucursal getIdClienteSucursal() {
        return idClienteSucursal;
    }

    public void setIdClienteSucursal(ClienteSucursal idClienteSucursal) {
        this.idClienteSucursal = idClienteSucursal;
    }

    public TipoHorario getIdTipoHorario() {
        return idTipoHorario;
    }

    public void setIdTipoHorario(TipoHorario idTipoHorario) {
        this.idTipoHorario = idTipoHorario;
    }

    @XmlTransient
    public List<SucursalHorarioDet> getSucursalHorariosDetList() {
        return sucursalHorariosDetList;
    }

    public void setSucursalHorariosDetList(List<SucursalHorarioDet> sucursalHorariosDetList) {
        this.sucursalHorariosDetList = sucursalHorariosDetList;
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
        if (!(object instanceof SucursalHorarioCab)) {
            return false;
        }
        SucursalHorarioCab other = (SucursalHorarioCab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.SucursalHorarioCab[ id=" + id + " ]";
    }
    
}
