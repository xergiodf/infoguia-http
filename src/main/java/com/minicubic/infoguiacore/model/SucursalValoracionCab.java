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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sucursal_valoraciones_cab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalValoracionCab.findAll", query = "SELECT s FROM SucursalValoracionCab s"), 
    @NamedQuery(name = "SucursalValoracionCab.findById", query = "SELECT s FROM SucursalValoracionCab s WHERE s.id = :id"), 
    @NamedQuery(name = "SucursalValoracionCab.findByPuntajeTotal", query = "SELECT s FROM SucursalValoracionCab s WHERE s.puntajeTotal = :puntajeTotal"), 
    @NamedQuery(name = "SucursalValoracionCab.findByAuditUsuario", query = "SELECT s FROM SucursalValoracionCab s WHERE s.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "SucursalValoracionCab.findByAuditFechaInsert", query = "SELECT s FROM SucursalValoracionCab s WHERE s.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "SucursalValoracionCab.findByAuditFechaUpdate", query = "SELECT s FROM SucursalValoracionCab s WHERE s.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class SucursalValoracionCab implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @JoinColumn(name = "id_cliente_sucursal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private ClienteSucursal clienteSucursal;

    @Column(name = "puntaje_total")
    @Getter
    @Setter
    private Float puntajeTotal;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalValoracionCab", fetch = FetchType.LAZY)
    private Collection<SucursalValoracionDet> sucursalValoracionDets;

    @XmlTransient
    public Collection<SucursalValoracionDet> getSucursalValoracionDets() {
        return sucursalValoracionDets;
    }
    public void setSucursalValoracionDets(Collection<SucursalValoracionDet> sucursalValoracionDets) {
        this.sucursalValoracionDets = sucursalValoracionDets;
    }
}
