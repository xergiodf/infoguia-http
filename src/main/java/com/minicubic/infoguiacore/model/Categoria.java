package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"), 
    @NamedQuery(name = "Categoria.findById", query = "SELECT c FROM Categoria c WHERE c.id = :id"), 
    @NamedQuery(name = "Categoria.findByDescripcion", query = "SELECT c FROM Categoria c WHERE c.descripcion = :descripcion")})
@ToString
@EqualsAndHashCode
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "descripcion")
    @Getter
    @Setter
    private String descripcion;

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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.LAZY)
    private Collection<ClienteCategoria> clienteCategorias;
    
    @ManyToMany(mappedBy = "categorias")
    private Collection<Cliente> clientes;

    @XmlTransient
    public Collection<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }

    @XmlTransient
    public Collection<ClienteCategoria> getClienteCategorias() {
        return clienteCategorias;
    }
    public void setClienteCategorias(Collection<ClienteCategoria> clienteCategorias) {
        this.clienteCategorias = clienteCategorias;
    }
}
