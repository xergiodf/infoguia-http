package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "sucursal_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalContacto.findAll", query = "SELECT s FROM SucursalContacto s"),
    @NamedQuery(name = "SucursalContacto.findById", query = "SELECT s FROM SucursalContacto s WHERE s.id = :id"),
    @NamedQuery(name = "SucursalContacto.findByContacto", query = "SELECT s FROM SucursalContacto s WHERE s.contacto = :contacto")})
public class SucursalContacto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "contacto")
    private String contacto;
    
    @JoinColumn(name = "id_tipo_contactos", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoContacto tipoContacto;
    
    @JoinColumn(name = "id_cliente_sucursal", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteSucursal clienteSucursal;

    public SucursalContacto() {
    }

    public SucursalContacto(Integer id) {
        this.id = id;
    }

    public SucursalContacto(Integer id, String contacto) {
        this.id = id;
        this.contacto = contacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(TipoContacto idTipoContactos) {
        this.tipoContacto = idTipoContactos;
    }

    public ClienteSucursal getClienteSucursal() {
        return clienteSucursal;
    }

    public void setClienteSucursal(ClienteSucursal idClienteSucursal) {
        this.clienteSucursal = idClienteSucursal;
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
        if (!(object instanceof SucursalContacto)) {
            return false;
        }
        SucursalContacto other = (SucursalContacto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.SucursalContacto[ id=" + id + " ]";
    }
    
}
