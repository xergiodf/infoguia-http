package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    @NamedQuery(name = "Cliente.findByCodigoCliente", query = "SELECT c FROM Cliente c WHERE c.codigoCliente = :codigoCliente"),
    @NamedQuery(name = "Cliente.findByParams", 
            query =     "SELECT DISTINCT c "
                    +   "FROM   Cliente c "
                    +   "LEFT JOIN c.clienteSucursales cs "
                    +   "WHERE  LOWER(c.nombreCompleto) LIKE LOWER(:params) "
                    +   "OR     LOWER(c.nombreCorto) LIKE LOWER(:params) "
                    +   "OR     LOWER(c.descripcionCompleta) LIKE LOWER(:params) "
                    +   "OR     LOWER(c.descripcionCorta) LIKE LOWER(:params) "
                    +   "OR     LOWER(cs.nombreSucursal) LIKE LOWER(:params)")
})
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
    private String codigoCliente;
    
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
    
    @JoinTable(name = "cliente_categorias", joinColumns = {
        @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    }, 
    inverseJoinColumns = {
        @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    })
    @ManyToMany
    private List<Categoria> categorias;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<ClientePublicacion> clientePublicaciones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<ClienteSucursal> clienteSucursales;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<ClienteCategoria> clienteCategorias;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<ClienteSuscripcion> clienteSuscripciones;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<ClienteOferta> clienteOfertas;


    @XmlTransient
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    @XmlTransient
    public List<ClienteSucursal> getClienteSucursales() {
        return clienteSucursales;
    }
    public void setClienteSucursales(List<ClienteSucursal> clienteSucursales) {
        this.clienteSucursales = clienteSucursales;
    }

    @XmlTransient
    public List<ClientePublicacion> getClientePublicaciones() {
        return clientePublicaciones;
    }
    public void setClientePublicaciones(List<ClientePublicacion> clientePublicaciones) {
        this.clientePublicaciones = clientePublicaciones;
    }

    @XmlTransient
    public List<Categoria> getCategorias() {
        return categorias;
    }
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    @XmlTransient
    public List<ClienteCategoria> getClienteCategorias() {
        return clienteCategorias;
    }
    public void setClienteCategorias(List<ClienteCategoria> clienteCategorias) {
        this.clienteCategorias = clienteCategorias;
    }

    @XmlTransient
    public List<ClienteSuscripcion> getClienteSuscripciones() {
        return clienteSuscripciones;
    }
    public void setClienteSuscripciones(List<ClienteSuscripcion> clienteSuscripciones) {
        this.clienteSuscripciones = clienteSuscripciones;
    }

    @XmlTransient
    public List<ClienteOferta> getClienteOfertas() {
        return clienteOfertas;
    }
    public void setClienteOfertas(List<ClienteOferta> clienteOfertas) {
        this.clienteOfertas = clienteOfertas;
    }
}
