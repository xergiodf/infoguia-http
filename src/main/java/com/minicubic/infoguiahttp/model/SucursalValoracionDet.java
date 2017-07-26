package com.minicubic.infoguiahttp.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "sucursal_valoraciones_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalValoracionDet.findAll", query = "SELECT s FROM SucursalValoracionDet s"), 
    @NamedQuery(name = "SucursalValoracionDet.findById", query = "SELECT s FROM SucursalValoracionDet s WHERE s.id = :id"), 
    @NamedQuery(name = "SucursalValoracionDet.findByUsuario", query = "SELECT s FROM SucursalValoracionDet s WHERE s.sucursalValoracionCab.id = :idCab AND s.usuario.id = :idUsuario"), 
    @NamedQuery(name = "SucursalValoracionDet.findByAuditUsuario", query = "SELECT s FROM SucursalValoracionDet s WHERE s.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "SucursalValoracionDet.findByAuditFechaInsert", query = "SELECT s FROM SucursalValoracionDet s WHERE s.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "SucursalValoracionDet.findByAuditFechaUpdate", query = "SELECT s FROM SucursalValoracionDet s WHERE s.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class SucursalValoracionDet implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @JoinColumn(name = "id_sucursal_valoracion_cab", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private SucursalValoracionCab sucursalValoracionCab;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Usuario usuario;

    @Column(name = "puntaje")
    @Getter
    @Setter
    private Float puntaje;

    @Column(name = "mensaje")
    @Getter
    @Setter
    private String mensaje;
    
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
}
