package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author xergio
 * @version 1
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
@ToString
@EqualsAndHashCode
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "codigo_cliente")
    @Getter
    @Setter
    private Integer codigoCliente;
    
    @Column(name = "nombre_completo")
    @Getter
    @Setter
    private String nombreCompleto;
    
    @Column(name = "nombre_corto")
    @Getter
    @Setter
    private String nombreCorto;
    
    @Column(name = "descripcion_completa", columnDefinition = "text")
    @Getter
    @Setter
    private String descripcionCompleta;
    
    @Column(name = "descripcion_corta")
    @Getter
    @Setter
    private String descripcionCorta;
    
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date fechaAlta;
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date fechaInicio;
    
    @Column(name = "audit_usuario")
    @Getter
    @Setter
    private String auditUsuario;
    
    @Column(name = "audit_fecha_insert", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaInsert;
    
    @Column(name = "audit_fecha_update", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaUpdate;
    
    @JoinTable(name = "clientes_categorias", joinColumns = {
        @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    }, 
    inverseJoinColumns = {
        @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    })
    @ManyToMany
    private Collection<Categoria> categorias;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<Usuario> usuarios;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClientePublicacion> clientePublicaciones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClienteSucursal> clienteSucursales;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClienteCategoria> clienteCategorias;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClienteSuscripcion> clienteSuscripciones;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClienteOferta> clienteOfertas;


    @XmlTransient
    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    @XmlTransient
    public Collection<ClienteSucursal> getClienteSucursales() {
        return clienteSucursales;
    }
    public void setClienteSucursales(Collection<ClienteSucursal> clienteSucursales) {
        this.clienteSucursales = clienteSucursales;
    }

    @XmlTransient
    public Collection<ClientePublicacion> getClientePublicaciones() {
        return clientePublicaciones;
    }
    public void setClientePublicaciones(Collection<ClientePublicacion> clientePublicaciones) {
        this.clientePublicaciones = clientePublicaciones;
    }

    @XmlTransient
    public Collection<Categoria> getCategorias() {
        return categorias;
    }
    public void setCategorias(Collection<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    @XmlTransient
    public Collection<ClienteCategoria> getClienteCategorias() {
        return clienteCategorias;
    }
    public void setClienteCategorias(Collection<ClienteCategoria> clienteCategorias) {
        this.clienteCategorias = clienteCategorias;
    }

    @XmlTransient
    public Collection<ClienteSuscripcion> getClienteSuscripciones() {
        return clienteSuscripciones;
    }
    public void setClienteSuscripciones(Collection<ClienteSuscripcion> clienteSuscripciones) {
        this.clienteSuscripciones = clienteSuscripciones;
    }

    @XmlTransient
    public Collection<ClienteOferta> getClienteOfertas() {
        return clienteOfertas;
    }
    public void setClienteOfertas(Collection<ClienteOferta> clienteOfertas) {
        this.clienteOfertas = clienteOfertas;
    }
}
