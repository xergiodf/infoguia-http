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

@Entity 
@Table(name = "sucursal_horarios_cab") 
@XmlRootElement 
@NamedQueries({ 
    @NamedQuery(name = "SucursalHorarioCab.findAll", query = "SELECT s FROM SucursalHorarioCab s"), 
    @NamedQuery(name = "SucursalHorarioCab.findById", query = "SELECT s FROM SucursalHorarioCab s WHERE s.id = :id")}) 
@ToString 
@EqualsAndHashCode 
public class SucursalHorarioCab implements Serializable { 
     
    private static final long serialVersionUID = 1L; 
     
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
    private Integer id; 
     
    @JoinColumn(name = "id_tipo_horario", referencedColumnName = "id") 
    @ManyToOne(optional = false, fetch = FetchType.LAZY) 
    @Getter 
    @Setter 
    private TipoHorario tipoHorario; 
     
    @JoinColumn(name = "id_cliente_sucursal", referencedColumnName = "id") 
    @ManyToOne(optional = false, fetch = FetchType.LAZY) 
    @Getter 
    @Setter 
    private ClienteSucursal clienteSucursal; 
     
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
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalHorarioCab", fetch = FetchType.LAZY) 
    private Collection<SucursalHorarioDet> sucursalHorariosDets; 
 
    @XmlTransient 
    public Collection<SucursalHorarioDet> getSucursalHorariosDets() { 
        return sucursalHorariosDets; 
    } 
    public void setSucursalHorariosDetList(Collection<SucursalHorarioDet> sucursalHorariosDets) { 
        this.sucursalHorariosDets = sucursalHorariosDets; 
    } 
} 
