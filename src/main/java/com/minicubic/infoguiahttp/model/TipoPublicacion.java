package com.minicubic.infoguiahttp.model;

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
 * @author hectorvillalba
 */
@Entity
@Table(name = "tipos_publicaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPublicacion.findAll", query = "SELECT t FROM TipoPublicacion t"),
    @NamedQuery(name = "TipoPublicacion.findById", query = "SELECT t FROM TipoPublicacion t WHERE t.id = :id"),
    @NamedQuery(name = "TipoPublicacion.findByDescripcion", query = "SELECT t FROM TipoPublicacion t WHERE t.descripcion = :descripcion")})
@ToString
@EqualsAndHashCode
public class TipoPublicacion implements Serializable {
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
    
    @Column(name = "codigo")
    @Getter
    @Setter
    private String codigo;

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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPublicacion", fetch = FetchType.LAZY)
    private Collection<ClientePublicacion> clientePublicaciones;

    @XmlTransient
    public Collection<ClientePublicacion> getClientePublicaciones() {
        return clientePublicaciones;
    }
    public void setClientePublicaciones(Collection<ClientePublicacion> clientePublicaciones) {
        this.clientePublicaciones = clientePublicaciones;
    }
}
