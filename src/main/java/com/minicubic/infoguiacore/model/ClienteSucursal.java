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
@Table(name = "cliente_sucursales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteSucursal.findAll", query = "SELECT c FROM ClienteSucursal c"),
    @NamedQuery(name = "ClienteSucursal.findById", query = "SELECT c FROM ClienteSucursal c WHERE c.id = :id"),
    @NamedQuery(name = "ClienteSucursal.findByNombreSucursal", query = "SELECT c FROM ClienteSucursal c WHERE c.nombreSucursal = :nombreSucursal"),
    @NamedQuery(name = "ClienteSucursal.findByDireccionFisica", query = "SELECT c FROM ClienteSucursal c WHERE c.direccionFisica = :direccionFisica"),
    @NamedQuery(name = "ClienteSucursal.findByCoordenadas", query = "SELECT c FROM ClienteSucursal c WHERE c.coordenadas = :coordenadas"),
    @NamedQuery(name = "ClienteSucursal.findByCliente", query = "SELECT c FROM ClienteSucursal c WHERE c.cliente.id = :clienteId")
})
public class ClienteSucursal implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalContacto> sucursalContactosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalValoracionCab> sucursalValoracionCabList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalHorarioCab> sucursalHorariosCabList;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nombre_sucursal")
    private String nombreSucursal;
    
    @Basic(optional = false)
    @Column(name = "direccion_fisica")
    private String direccionFisica;
    
    @Basic(optional = false)
    @Column(name = "coordenadas")
    private String coordenadas;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;

    public ClienteSucursal() {
    }

    public ClienteSucursal(Integer id) {
        this.id = id;
    }

    public ClienteSucursal(Integer id, String direccionFisica, String coordenadas) {
        this.id = id;
        this.direccionFisica = direccionFisica;
        this.coordenadas = coordenadas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public void setDireccionFisica(String direccionFisica) {
        this.direccionFisica = direccionFisica;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
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
        if (!(object instanceof ClienteSucursal)) {
            return false;
        }
        ClienteSucursal other = (ClienteSucursal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.ClienteSucursales[ id=" + id + " ]";
    }

    @XmlTransient
    public List<SucursalContacto> getSucursalContactosList() {
        return sucursalContactosList;
    }

    public void setSucursalContactosList(List<SucursalContacto> sucursalContactosList) {
        this.sucursalContactosList = sucursalContactosList;
    }

    @XmlTransient
    public List<SucursalValoracionCab> getSucursalValoracionCabList() {
        return sucursalValoracionCabList;
    }

    public void setSucursalValoracionCabList(List<SucursalValoracionCab> sucursalValoracionCabList) {
        this.sucursalValoracionCabList = sucursalValoracionCabList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente idCliente) {
        this.cliente = idCliente;
    }

    @XmlTransient
    public List<SucursalHorarioCab> getSucursalHorariosCabList() {
        return sucursalHorariosCabList;
    }

    public void setSucursalHorariosCabList(List<SucursalHorarioCab> sucursalHorariosCabList) {
        this.sucursalHorariosCabList = sucursalHorariosCabList;
    }
    
}
