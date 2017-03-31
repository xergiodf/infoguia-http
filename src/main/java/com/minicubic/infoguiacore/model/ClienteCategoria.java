package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
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
@Table(name = "cliente_categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteCategoria.findAll", query = "SELECT c FROM ClienteCategoria c"), 
    @NamedQuery(name = "ClienteCategoria.findByIdCliente", query = "SELECT c FROM ClienteCategoria c WHERE c.clienteCategoriaPK.idCliente = :idCliente"), 
    @NamedQuery(name = "ClienteCategoria.findByIdCategoria", query = "SELECT c FROM ClienteCategoria c WHERE c.clienteCategoriaPK.idCategoria = :idCategoria"), 
    @NamedQuery(name = "ClienteCategoria.findById", query = "SELECT c FROM ClienteCategoria c WHERE c.clienteCategoriaPK = :id")})
@ToString
@EqualsAndHashCode
public class ClienteCategoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    @Getter
    @Setter
    protected ClienteCategoriaPK clienteCategoriaPK;
    
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Categoria categoria;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Cliente cliente;
    
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
    
    public ClienteCategoria() {}

    public ClienteCategoria(long idCliente, int idCategoria) {
        this.clienteCategoriaPK = new ClienteCategoriaPK(idCliente, idCategoria);
    }
}
