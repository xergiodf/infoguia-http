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

@Entity 
@Table(name = "sucursal_horarios_det") 
@XmlRootElement 
@NamedQueries({ 
    @NamedQuery(name = "SucursalHorarioDet.findAll", query = "SELECT s FROM SucursalHorarioDet s"), 
    @NamedQuery(name = "SucursalHorarioDet.findById", query = "SELECT s FROM SucursalHorarioDet s WHERE s.id = :id"), 
    @NamedQuery(name = "SucursalHorarioDet.findByHoraDesde", query = "SELECT s FROM SucursalHorarioDet s WHERE s.horaDesde = :horaDesde"), 
    @NamedQuery(name = "SucursalHorarioDet.findByHoraHasta", query = "SELECT s FROM SucursalHorarioDet s WHERE s.horaHasta = :horaHasta")}) 
@ToString 
@EqualsAndHashCode 
public class SucursalHorarioDet implements Serializable { 
    private static final long serialVersionUID = 1L; 
     
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
    @Getter 
    @Setter 
    private Integer id; 
     
    @JoinColumn(name = "id_sucursal_horario_cab", referencedColumnName = "id") 
    @ManyToOne(optional = false) 
    @Getter 
    @Setter 
    private SucursalHorarioCab sucursalHorarioCab; 
     
    @Column(name = "dias") 
    @Getter 
    @Setter 
    private String dias; 
 
    @Column(name = "hora_desde") 
    @Getter 
    @Setter 
    private String horaDesde; 
 
    @Column(name = "hora_hasta") 
    @Getter 
    @Setter 
    private String horaHasta; 
 
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