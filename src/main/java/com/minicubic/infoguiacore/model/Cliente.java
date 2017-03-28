package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xergio
 */
@Entity
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id"),
    @NamedQuery(name = "Cliente.findByNombreCompleto", query = "SELECT c FROM Cliente c WHERE c.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "Cliente.findByNombreCorto", query = "SELECT c FROM Cliente c WHERE c.nombreCorto = :nombreCorto"),
    @NamedQuery(name = "Cliente.findByDescripcionCorta", query = "SELECT c FROM Cliente c WHERE c.descripcionCorta = :descripcionCorta"),
    @NamedQuery(name = "Cliente.findByFechaAlta", query = "SELECT c FROM Cliente c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Cliente.findByFechaInicio", query = "SELECT c FROM Cliente c WHERE c.fechaInicio = :fechaInicio")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    
    @Column(name = "nombre_corto")
    private String nombreCorto;
    
    @Column(name = "descripcion_completa", columnDefinition = "text")
    private String descripcionCompleta;
    
    @Column(name = "descripcion_corta")
    private String descripcionCorta;
     
    @Basic(optional = false)
    @Column(name = "fecha_alta", updatable= false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @JoinTable(name = "clientes_categorias", joinColumns = {
        @JoinColumn(name = "clientes_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "categorias_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Categoria> categoriaCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Usuario> usuarioCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClientePublicacion> clientePublicacionesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClienteSucursal> clienteSucursalesCollection;

    public Cliente() {
    }

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(Long id, String nombreCompleto, Date fechaAlta) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.fechaAlta = fechaAlta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDescripcionCompleta() {
        return descripcionCompleta;
    }

    public void setDescripcionCompleta(String descriptionCompleta) {
        this.descripcionCompleta = descriptionCompleta;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.Cliente[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ClienteSucursal> getClienteSucursalesCollection() {
        return clienteSucursalesCollection;
    }

    public void setClienteSucursalesCollection(Collection<ClienteSucursal> clienteSucursalesCollection) {
        this.clienteSucursalesCollection = clienteSucursalesCollection;
    }

    @XmlTransient
    public Collection<ClientePublicacion> getClientePublicacionesCollection() {
        return clientePublicacionesCollection;
    }

    public void setClientePublicacionesCollection(Collection<ClientePublicacion> clientePublicacionesCollection) {
        this.clientePublicacionesCollection = clientePublicacionesCollection;
    }

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }
    
}
