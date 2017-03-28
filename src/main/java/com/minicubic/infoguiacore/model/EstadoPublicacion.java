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
@Table(name = "estados_publicaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPublicacion.findAll", query = "SELECT e FROM EstadoPublicacion e"),
    @NamedQuery(name = "EstadoPublicacion.findById", query = "SELECT e FROM EstadoPublicacion e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoPublicacion.findByDescripcion", query = "SELECT e FROM EstadoPublicacion e WHERE e.descripcion = :descripcion")})
public class EstadoPublicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoPublicacion", fetch = FetchType.LAZY)
    private List<ClientePublicacion> clientePublicacionesList;

    public EstadoPublicacion() {
    }

    public EstadoPublicacion(Integer id) {
        this.id = id;
    }

    public EstadoPublicacion(Integer id, String descripcion) {
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
    public List<ClientePublicacion> getClientePublicacionesList() {
        return clientePublicacionesList;
    }

    public void setClientePublicacionesList(List<ClientePublicacion> clientePublicacionesList) {
        this.clientePublicacionesList = clientePublicacionesList;
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
        if (!(object instanceof EstadoPublicacion)) {
            return false;
        }
        EstadoPublicacion other = (EstadoPublicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.EstadoPublicacion[ id=" + id + " ]";
    }
    
}
